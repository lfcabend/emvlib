package org.emv.gui

import java.util.{Currency, Locale}
import java.util.concurrent.{Executors, ScheduledExecutorService}

import com.neovisionaries.i18n.CountryCode
import org.emv._
import org.lau.visa.dataelements.TerminalTransactionQualifiers
import org.emv.tlv._
import org.iso7816.AID
import org.joda.time.LocalDate

import scalaz.concurrent._
import scodec.bits._

/**
  * Created by Lau on 5/11/2017.
  */
object ApplicationContext {

  val auth: Authorizer = new Authorizer() {
    override def authorize(terminalState: TerminalState): Task[TerminalState] = Task(terminalState)
  }

  val ui: UserInterface = new KernelController

  implicit val scheduler = Executors.newScheduledThreadPool(1)

  //  val connectionConfig: ConnectionConfig = new ConnectionConfig() {}
  //
  //  val card: CardTrait = LibNFCCard
  val connectionConfig = new PCSCConnectionConfig {
    override val preferredReader: String = "OMNIKEY CardMan 5x21-CL 0"
    override val waitForCard: Int = 10000
  }
  val card: CardTrait = PCSCCard

  val terminalEnv: TerminalEnv = new TerminalEnv {

    override def authorizer: Authorizer = auth

    override def userInterface: UserInterface = ui

    override def executor: ScheduledExecutorService = scheduler
  }

  val ttq = TerminalTransactionQualifiers(hex"00000000").
    withQVSDCSupportedSet

  val terminalCountryCode = TerminalCountryCode(CountryCode.NL)
  val txnCurr = TransactionCurrencyCode(Currency.getInstance(Locale.UK))
  val brands = List(BrandParameters(AID(hex"A0000000031010"), List(ttq)))
  val mnl = MerchantNameLocation(ByteVector("ShiptlDrop/BE       ".getBytes()))
  val generalTags = GeneralParameters(List(
    txnCurr,
    terminalCountryCode,
    mnl))

  val config = TerminalConfig(generalTags, brands)

  val transactionDate = TransactionDate(LocalDate.now)
  val amountAuthorized = AmountAuthorized(hex"000000000120")
  val amountOther = AmountOther(hex"000000000000")
  val tvr = TerminalVerificationResults()
  val txnType = TransactionType(hex"00")
  val un = UnpredictableNumber(hex"01010010")
  val transientData = TerminalTransientData(Nil, List(
    amountAuthorized,
    amountOther,
    tvr,
    transactionDate,
    txnType,
    un
  ))

  val terminalState: TerminalState = TerminalState(config, transientData, TransactionTransmissions())
}
