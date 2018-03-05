import java.util.concurrent.{Executors, ScheduledExecutorService}
import java.util.{Currency, Locale}

import com.neovisionaries.i18n.CountryCode
import com.typesafe.scalalogging.LazyLogging
import org.emv.gui.TerminalFX
import org.emv.tlv._
import org.emv.{TerminalEnv, _}
import org.iso7816.AID
import org.joda.time.LocalDate
import org.lau.visa.dataelements.TerminalTransactionQualifiers
import scodec.bits._

import scalaz.concurrent.Task
import scalaz.{-\/, Reader, \/-}

/**
  * Created by lau on 12/6/16.
  */
object TestMain extends App with LazyLogging {

  //  logger.debug("starting app")
  //
  //
  //  val p = for {
  //    cntx <- LibNFCCard.initialize
  //    cntx <- LibNFCCard.waitForCardOnTerminal(cntx)
  //    r <- LibNFCCard.transmit(cntx, hex"00A404000E325041592E5359532E444446303100")
  //  } yield(r)
  //
  //
  //  p.unsafePerformSyncAttempt match {
  //    case \/-(x) => println(x)
  //    case -\/(e)=> println(s"did not work: ${e.getMessage}"); e.printStackTrace()
  //  }

//  val ttq = TerminalTransactionQualifiers(hex"00000000").
//    withQVSDCSupportedSet
//
//  val terminalCountryCode = TerminalCountryCode(CountryCode.NL)
//  val txnCurr = TransactionCurrencyCode(Currency.getInstance(Locale.UK))
//  val brands = List(BrandParameters(AID(hex"A0000000031010"), List(ttq)))
//  val mnl = MerchantNameLocation(ByteVector("ShiptlDrop/BE       ".getBytes()))
//  val generalTags = GeneralParameters(List(
//    txnCurr,
//    terminalCountryCode,
//    mnl))
//
//  val config = TerminalConfig(generalTags, brands)
//  val pCSCConnectionConfig = new PCSCConnectionConfig {
//    override val preferredReader: String = "OMNIKEY CardMan 5x21-CL 0"
//    override val waitForCard: Int = 10000
//  }
//
//  val transactionDate = TransactionDate(LocalDate.now)
//  val amountAuthorized = AmountAuthorized(hex"000000000120")
//  val amountOther = AmountOther(hex"000000000000")
//  val tvr = TerminalVerificationResults()
//  val txnType = TransactionType(hex"00")
//  val un = UnpredictableNumber(hex"01010010")
//  val transientData = TerminalTransientData(Nil, List(
//    amountAuthorized,
//    amountOther,
//    tvr,
//    transactionDate,
//    txnType,
//    un
//  ))
//
//  val terminalState: TerminalState = TerminalState(config, transientData, TransactionTransmissions())
//
//  val auth: Authorizer = new Authorizer() {
//    override def authorize(terminalState: TerminalState): Task[TerminalState] = Task(terminalState)
//  }
//
//  val userI: UserInterface = new UserInterface() {
//    override def isTransactionCanceled: Boolean = false
//  }
//
//  val card: CardTrait = LibNFCCard
//
//  import TerminalProcessor._
//
//  implicit def scheduler = Executors.newScheduledThreadPool(1)
//  val terminalEnv: TerminalEnv = new TerminalEnv {
//    override def authorizer: Authorizer = auth
//
//    override def userInterface: UserInterface = userI
//
//    override def executor: ScheduledExecutorService = scheduler
//  }
//  val terminalState1 = processTransaction(pCSCConnectionConfig, card , terminalState)
//  println("starting...")
//  terminalState1(terminalEnv).unsafePerformSyncAttempt match {
//    case \/-(x) => println(x)
//    case -\/(e) => println(s"did not work: ${e.getMessage}"); e.printStackTrace()
//  }

  TerminalFX.main(args)


}
