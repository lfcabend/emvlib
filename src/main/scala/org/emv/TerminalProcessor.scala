package org.emv

import java.util.concurrent.{ScheduledExecutorService, TimeUnit}
import javax.smartcardio.Card

import org.emv.tlv.EMVTLV.EMVTLVType
import org.emv.tlv._
import org.iso7816.{AID, NormalProcessingNoFurtherQualification, Select, SelectResponse}
import org.lau.tlv.ber._
import scodec.bits._
import EMVCardHandler._
import com.typesafe.scalalogging.LazyLogging
import fastparse.byte.all.Parser
import org.emv.commands.{GPOResponseFormat1, GPOResponseFormat2}

import scala.collection.immutable.::
import scala.concurrent.duration.Duration
import scalaz._
import scalaz.concurrent._


/**
  * Created by lau on 7/6/16.
  */
object TerminalProcessor extends LazyLogging {

  type TransactionSt = ReaderT[Task, TerminalEnv, TerminalState]

  type ReaderTTransactionState = ReaderT[Task, TerminalEnv, TerminalState]

  object ReaderTTransactionState {

    def apply(f: TerminalEnv => Task[TerminalState]) =
      Kleisli[Task, TerminalEnv, TerminalState](f)
  }

  type ReaderTConnectionContext = ReaderT[Task, TerminalEnv, ConnectionContext]

  object ReaderTConnectionContext {

    def apply(f: TerminalEnv => Task[ConnectionContext]) =
      Kleisli[Task, TerminalEnv, ConnectionContext](f)
  }

  def initializeCard(card: CardTrait, connectionConfig: ConnectionConfig) =
    ReaderTConnectionContext(_ => card.initialize(connectionConfig))

  def connectToCard(card: CardTrait, context: ConnectionContext) =
    ReaderTConnectionContext(_ => card.waitForCardOnTerminal(context))

  def authorize(terminalState: TerminalState): TransactionSt =
    ReaderTTransactionState(env => {
      implicit val ex = env.executor
      env.authorizer.authorize(terminalState).timed(1000L)
    })

  def processTransaction(connectionConfig: ConnectionConfig,
                         card: CardTrait,
                         terminalState: TerminalState): TransactionSt = for {

    context0 <- initializeCard(card, connectionConfig)

    _ <- reportTerminalInitialized(context0, terminalState)

    context <- connectToCard(card, context0)

    _ <- reportCardConnected(context, terminalState)

    ts1 <- performSelectPPSE(context, card, terminalState)

    _ <- failOnError(ts1, _.isPPSESuccessful, "PPSE failed")

    _ <- checkIfUserCanceledTheTransaction

    ts2 <- createAIDCandidateList(ts1)

    _ <- failOnError(ts2, _.isCandidateListNotEmpty, "No candidates")

    _ <- checkIfUserCanceledTheTransaction

    ts3 <- performFinalSelectApplication(context, card, ts2)

    _ <- failOnError(ts3, _.isSelectionSuccessful, "select failed")

    _ <- checkIfUserCanceledTheTransaction

    processor <- getBrandProcessor(ts3)

    ts4 <- processor.process(context, card, ts3)

    ts5 <- authorize(ts4)

  } yield (ts5)

  def checkIfUserCanceledTheTransaction: ReaderT[Task, TerminalEnv, Boolean] =
    Kleisli(env =>
      Task(
        env.userInterface.isTransactionCanceled
      ).flatMap({
        case true => Task.fail(new RuntimeException("User canceled txn"))
        case false => Task(false)
      }))


  def createAIDCandidateList(terminalState: TerminalState)= ReaderTTransactionState(_ => {

    val ppseTrans = terminalState.transmissions.selectPPSETransmission
    ppseTrans match {
      case Some(SelectTransmission(_, Some(SelectResponse(Some(fciTemplate), NormalProcessingNoFurtherQualification)))) => {}
        createAIDCandidateListWithFciTemplate(terminalState, fciTemplate)
      case _ => Task.fail(new RuntimeException("did not perform PPSE successfully"))
    }
  })

  def createAIDCandidateListWithFciTemplate(terminalState: TerminalState,
                                            fciTemplate: EMVTLVType): Task[TerminalState] = fciTemplate -> (berTag"61") match {
    case Some(applicationTemplates) => {
      val intersection = getIntersectionBetweenTerminalAndCard(applicationTemplates, terminalState)
      val updatedTerminalState = terminalState.withAIDCandidateList(intersection)
      Task {
        logger.debug(s"Candidate list: $intersection")
        updatedTerminalState
      }
    }
    case _ => Task.fail(new RuntimeException("Could not find the application templates"))
  }

  def getBrandProcessor(terminalState: TerminalState):  ReaderT[Task, TerminalEnv, Processor] = Kleisli(env =>
    terminalState.getSelectedAID() match {
      case Some(x) => BrandProcessorMap.mapping.get(x) match {
        case Some(x) => Task(x)
        case None => Task.fail(new RuntimeException(s"No mapping for aid: ${terminalState.getSelectedAID()}"))
      }
      case None => Task.fail(new RuntimeException(s"No selected application"))
    })


  def failOnError(terminalState: TerminalState, check: TerminalState => Boolean, msg: String) =
    ReaderTTransactionState(_ =>
      if (!terminalState.isPPSESuccessful) Task.fail(new RuntimeException(msg))
      else Task(terminalState))

  def performSelectPPSE(context: ConnectionContext, card: CardTrait, terminalState: TerminalState) =
    ReaderTTransactionState(env =>
      performSelect(context, card, AID.PPSE).
      map(transmission => reportAndUpdateState[Select, SelectResponse, SelectTransmission]
        (env.userInterface, transmission, terminalState, terminalState.withSelectPPSE))
    )

  def reportAndUpdateState[A, B, T <: Transmission[A, B]](userInterface: UserInterface,
                           transmission: T,
                           terminalState: TerminalState,
                           updateFunction: T => TerminalState): TerminalState = {

    val newState = updateFunction(transmission)
    userInterface.reportCommandProcessed(transmission, newState)
    newState

  }

  def performFinalSelectApplication(context: ConnectionContext, card: CardTrait, terminalState: TerminalState) = ReaderTTransactionState(_ => {
    val candidateList = terminalState.transientData.candidateList
    if (candidateList.isEmpty) {
      Task.fail(new RuntimeException("Empty candidate list"))
    } else {
      val aidOption = candidateList.headOption
      val parser = terminalState.getBrandParser(aidOption)
      (aidOption, parser) match {
        case (Some(x), Some(y)) => performSelectTillSuccess(context, card, terminalState, x, y)
        case (_, None) => Task.fail(new RuntimeException("no aid in candidate list"))
        case (None, _) => Task.fail(new RuntimeException("no parser"))
      }
    }
  })

  def performSelectTillSuccess(context: ConnectionContext, card: CardTrait, terminalState: TerminalState,
                               aid: AID, parser: Parser[EMVTLVType]): Task[TerminalState] = {
    //    performSelect(context, card, aid, parser).map(terminalState.withSelectApplication(_))
    performSelect(context, card, aid, parser).flatMap({
      case r1@SelectTransmission(_, Some(SelectResponse(_, NormalProcessingNoFurtherQualification))) => //success
        Task(terminalState.withSelectApplication(r1))
      case _ if terminalState.transientData.candidateList.size > 1 => {
        //go into recursion
        val newCandidateList = terminalState.transientData.candidateList.tail
        val newTerminalState = terminalState.withAIDCandidateList(newCandidateList)
        val aid = newCandidateList.head
        performSelectTillSuccess(context, card, newTerminalState, aid, parser)
      }
      case _ => Task.fail(new RuntimeException("Could not select an AID successfully"))
    })
  }

  def processGetProcessingOptions(context: ConnectionContext, card: CardTrait, terminalState: TerminalState) =
    ReaderTTransactionState(_ => {terminalState.transmissions.selectTransmission match {
      case Some(SelectTransmission(Some(_), Some(SelectResponse(Some(d), NormalProcessingNoFurtherQualification)))) =>
        processGetProcessingOptionsBasedOnFciTemplate(context, card, terminalState, d).
          map(terminalState.withGetProcessingOptions)
      case _ => Task.fail(new RuntimeException("Select was no processed successfully"))
    }})

  def processReadRecords(context: ConnectionContext, card: CardTrait, terminalState: TerminalState): TransactionSt =
    ReaderTTransactionState(_ => {
    terminalState.transmissions.gpoTransmission match {
      case Some(GPOTransmission(_, Some(GPOResponseFormat1(Some(format1), NormalProcessingNoFurtherQualification)))) =>
        processReadRecords(context, card, terminalState, format1.afl)
      case Some(GPOTransmission(_, Some(GPOResponseFormat2(Some(format2), NormalProcessingNoFurtherQualification)))) =>
        format2.constructedValue.getTag(ApplicationFileLocator.tag) match {
          case (Some(afl: ApplicationFileLocator)) => processReadRecords(context, card, terminalState, afl)
          case _ => Task.fail(new RuntimeException("No AFL"))
        }
      case _ => Task.fail(new RuntimeException("GPO was no processed succesfully"))
    }})

  def processReadRecords(context: ConnectionContext, card: CardTrait, terminalState: TerminalState, afl: ApplicationFileLocator): Task[TerminalState] = {
    val aidOption = terminalState.transientData.candidateList.headOption
    val parser = terminalState.getBrandParser(aidOption)
    parser match {
      case Some(x) => readRecords(context, card, afl, x).map(terminalState.withReadRecordsTransmission)
      case None => Task.fail(new RuntimeException("no parser"))
    }
  }

  def processGetProcessingOptionsBasedOnFciTemplate(context: ConnectionContext, card: CardTrait,
                                                    terminalState: TerminalState,
                                                    fciTemplate: BerTLV): Task[GPOTransmission] = {
    val aidOption = terminalState.transientData.candidateList.headOption
    val parser = terminalState.getBrandParser(aidOption)
    parser match {
      case Some(x) => for {
        pdol <- getPDOL(fciTemplate)
        trans <- performGPO(context, card, pdol, terminalState.terminalTLV(), x)
      } yield trans
      case None => Task.fail(new RuntimeException("no parser"))
    }

  }

  def getPDOL(fciTemplate: BerTLV): Task[Option[ProcessingOptionsDataObjectList]] =
    fciTemplate.select(List(PathEx(ProcessingOptionsDataObjectList.tag))) match {
      case Some((pdol: ProcessingOptionsDataObjectList) :: _) => Task(Some(pdol))
      case _ => Task(None)
    }

  def getIntersectionBetweenTerminalAndCard(applicationTemplates: List[BerTLV], state: TerminalState): List[AID] = {
    val supportedAids: Set[AID] = state.supportedBrands()
    getIntersectionBetweenTerminalAndCard(applicationTemplates, supportedAids)
      .sortBy(_._2)
      .map(_._1)
  }

  def getIntersectionBetweenTerminalAndCard(applicationTemplates: List[BerTLV], supportedAids: Set[AID]): List[(AID, Int)] =
    applicationTemplates.flatMap({
      case tem@ApplicationTemplate(templateTags) => {
        val fileName = templateTags.getTag(ApplicationDedicatedFileName.tag)
        val priority = templateTags.getTag(ApplicationPriorityIndicator.tag)
        (fileName, priority) match {
          case (Some(ApplicationDedicatedFileName(a)), Some(v@ApplicationPriorityIndicator(_)))
            if supportedAids.contains(a) => List((a, v.number))
          case (Some(ApplicationDedicatedFileName(a)), _)
            if supportedAids.contains(a) => List((a, Int.MaxValue))
          case _ => Nil
        }
      }
      case _ => Nil
    })

  def reportTerminalInitialized(connectionContext: ConnectionContext, terminalState: TerminalState): ReaderT[Task, TerminalEnv, Unit] =
    Kleisli(env =>
    Task(
      env.userInterface.terminalInitialized(connectionContext, terminalState)
    ))

  def reportCardConnected(connectionContext: ConnectionContext, terminalState: TerminalState): ReaderT[Task, TerminalEnv, Unit] =
    Kleisli(env =>
      Task(
        env.userInterface.cardConnected(connectionContext, terminalState)
      ))

  def reportCommandProcessed(connectionContext: ConnectionContext, terminalState: TerminalState): ReaderT[Task, TerminalEnv, Unit] =
    Kleisli(env =>
      Task(
        env.userInterface.cardConnected(connectionContext, terminalState)
      ))
}
