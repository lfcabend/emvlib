package org.emv.tlv

import java.util.{Currency, Locale}
import java.util.concurrent.{Executors, ScheduledExecutorService}

import com.neovisionaries.i18n.CountryCode
import org.emv.EMVCardHandler._
import org.emv._
import org.emv.commands._
import org.iso7816._
import org.joda.time.LocalDate
import org.lau.tlv.ber._
import org.lau.visa.dataelements.TerminalTransactionQualifiers
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}
import scodec.bits._

import scalaz.concurrent.Task
import scalaz.{-\/, \/-}

/**
  * Created by lau on 3/7/17.
  */
class TerminalProcessorTest extends FlatSpec with Matchers with MockFactory {

  "a terminal processor" should " be able to processs a happy flow visa transaction" in {
    val context: ConnectionContext = mock[ConnectionContext]
    val card: CardTrait = mock[CardTrait]
    val connectionConfig: ConnectionConfig = mock[ConnectionConfig]

    val ttq = TerminalTransactionQualifiers(hex"90901912")
    val terminalCountryCode = TerminalCountryCode(CountryCode.NL)
    val txnCurr = TransactionCurrencyCode(Currency.getInstance(Locale.UK))
    val brands = List(VisaBrandParameters(AID(hex"A0000000031010"), ttq))
    val generalTags = GeneralParameters(
      Some(terminalCountryCode),
      Some(txnCurr),
      None)

    val config = TerminalConfig(generalTags, brands)

    val transactionDate = TransactionDate(LocalDate.now)
    val amountAuthorized = AmountAuthorized(hex"000000000120")
    val amountOther = AmountOther(hex"000000000000")
    val tvr = TerminalVerificationResults()
    val txnType = TransactionType(hex"00")
    val un = UnpredictableNumber(hex"01010010")
    val transientData = TerminalTransientData(Nil,
      Some(transactionDate),
      Some(amountAuthorized),
      Some(amountOther),
      Some(tvr),
      Some(txnType),
      Some(un)
    )

    val terminalState: TerminalState = TerminalState(config, transientData, TransactionTransmissions())

    (card.initialize _).expects(*).returns(Task(context))

    (card.waitForCardOnTerminal _).expects(*).returns(Task(context))

    val auth1: Authorizer = mock[Authorizer]
    val userInterface1: UserInterface = mock[UserInterface]

    (userInterface1.isTransactionCanceled _).expects().anyNumberOfTimes.returning(false)

    (auth1.authorize _).expects(*).onCall{x: TerminalState => Task(x)}

    (card.transmit _).expects(*, argThat[ByteVector](_ == hex"00A404000E325041592E5359532E444446303100")).
      returns(Task(hex"6F2D840E325041592E5359532E4444463031A51BBF0C1861164F07A0000000031010500B56495341204352454449549000"))

    (card.transmit _).expects(*, argThat[ByteVector](_ == hex"00A4040007A000000003101000")).
      returns(Task(hex"6F438407A0000000031010A538500B56495341204352454449549F38189F66049F02069F03069F1A0295055F2A029A039C019F37045F2D02656EBF0C089F5A0531323334359000"))

    val pdolValue = ttq.value ++ amountAuthorized.value ++ amountOther.value ++ terminalCountryCode.value ++ tvr.value ++ txnCurr.value ++
      transactionDate.value ++ txnType.value ++ un.value

    (card.transmit _).expects(*, argThat[ByteVector](
      _.toHex.toUpperCase() === s"80A80000238321${pdolValue.toHex.toUpperCase()}00"
    )).
      returns(Task(hex"77659F26086C7C0BF5DC7F9159940408030300820200409F360200019F6C0201809F2701809F6E04238C00009F10201F4301512000000000010203040403450100000000000000000000000000000057124761739001010010D201220112345123456F5F3401009000"))

    (card.transmit _).expects(*, argThat[ByteVector](_ == hex"00B2030C00")).
      returns(Task(hex"702B5F200E4E616D6530696E205265636F72645F280208409F7C04010201029F070200809F19060908070908079000"))


    import TerminalProcessor._

    implicit def scheduler = Executors.newScheduledThreadPool(5)

    val terminalState1 = processTransaction(connectionConfig, card, terminalState)

    val terminalEnv: TerminalEnv = new TerminalEnv {
      override def authorizer: Authorizer = auth1

      override def userInterface: UserInterface = userInterface1

      override def executor: ScheduledExecutorService = scheduler
    }

    terminalState1(terminalEnv).unsafePerformSyncAttempt match {
      case \/-(TerminalState(config, transientData, transmissions)) => {
        println(transmissions)
      }
      case -\/(e) => {
        fail(e.toString)
      }
      case _ => fail("result did not match expected value")
    }
  }

}
