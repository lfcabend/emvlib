package org.emv

import java.util.concurrent.ScheduledExecutorService
import javax.smartcardio.Card

import org.emv.tlv.EMVTLV.EMVTLVType
import org.emv.tlv._
import org.iso7816.{AID, NormalProcessingNoFurtherQualification, SelectResponse}
import org.lau.tlv.ber._
import scodec.bits._
import EMVCardHandler._
import org.emv.commands.{GPOResponseFormat1, GPOResponseFormat2}

import scala.collection.immutable.::
import scalaz.concurrent.Task

/**
  * Created by lau on 7/6/16.
  */
object TerminalProcessor {


  def processTransaction(authorizer: Authorizer,
                         userInterface: UserInterface,
                         context: ConnectionContext,
                         card: CardTrait,
                         terminalState: TerminalState)
                        (implicit ex: ScheduledExecutorService): Task[TerminalState] = for {

    ts1 <- performSelectPPSE(context, card, terminalState)

    _ <- failOnError(ts1, _.isPPSESuccessful, "PPSE failed")

    _ <- checkIfUserCanceledTheTransaction(userInterface)

    ts2 <- createAIDCandidateList(ts1)

    _ <- failOnError(ts2, _.isCandidateListNotEmpty, "No candidates")

    _ <- checkIfUserCanceledTheTransaction(userInterface)

    ts3 <- performSelectApplication(context, card, ts2)

    _ <- failOnError(ts3, _.isSelectionSuccessful, "select failed")

    _ <- checkIfUserCanceledTheTransaction(userInterface)

    processor <- getBrandProcessor(ts3)

    ts4 <- processor.process(userInterface, context, card, ts3)

//    ts5 <- authorizer.authorize(ts4).timed(1000L)

  } yield (ts4)

  def checkIfUserCanceledTheTransaction(userInterface: UserInterface): Task[Boolean] = Task(
    userInterface.isTransactionCanceled
  ).flatMap({
    case true => Task.fail(new RuntimeException("User canceled txn"))
    case false => Task(false)
  })


  def createAIDCandidateList(terminalState: TerminalState): Task[TerminalState] = {

    val ppseTrans = terminalState.transmissions.selectPPSETransmission
    ppseTrans match {
      case Some(SelectTransmission(_, Some(SelectResponse(Some(fciTemplate), NormalProcessingNoFurtherQualification)))) => {}
        createAIDCandidateListWithFciTemplate(terminalState, fciTemplate)
      case _ => Task.fail(new RuntimeException("did not perform PPSE succesfully"))
    }
  }

  def createAIDCandidateListWithFciTemplate(terminalState: TerminalState,
                                            fciTemplate: EMVTLVType): Task[TerminalState] = fciTemplate -> (berTag"61") match {
    case Some(applicationTemplates) => {
      val intersection = getIntersectionBetweenTerminalAndCard(applicationTemplates, terminalState)
      val updatedTerminalState = terminalState.withAIDCandidateList(intersection)
      Task(updatedTerminalState)
    }
    case _ => Task.fail(new RuntimeException("Could not find the application templates"))
  }

  def getBrandProcessor(terminalState: TerminalState): Task[Processor] =
    terminalState.getSelectedAID() match {
      case Some(x) => BrandProcessorMap.mapping.get(x) match {
        case Some(x) => Task(x)
        case None => Task.fail(new RuntimeException(s"No mapping for aid: ${terminalState.getSelectedAID()}"))
      }
      case None => Task.fail(new RuntimeException(s"No selected application"))
    }


  def failOnError(terminalState: TerminalState, check: TerminalState => Boolean, msg: String): Task[TerminalState] =
    if (!terminalState.isPPSESuccessful) Task.fail(new RuntimeException(msg)) else Task(terminalState)

  def performSelectPPSE(context: ConnectionContext, card: CardTrait, terminalState: TerminalState): Task[TerminalState] =
    performSelect(context, card, AID.PPSE).map(terminalState.withSelectPPSE(_))

  def performSelectApplication(context: ConnectionContext, card: CardTrait, terminalState: TerminalState): Task[TerminalState] = {
    val candidateList = terminalState.transientData.candidateList
    if (candidateList.isEmpty) {
      Task.fail(new RuntimeException("Empty candidate list"))
    } else {
      val aidOption = candidateList.headOption
      val parser = terminalState.getBrandParser(aidOption)
      (aidOption, parser) match {
        case (Some(x), Some(y)) => performSelect(context, card, x, y).map(terminalState.withSelectApplication(_))
        case (_, None) => Task.fail(new RuntimeException("no aid in candidate list"))
        case (None, _) => Task.fail(new RuntimeException("no parser"))
      }
    }
  }

  def processGetProcessingOptions(context: ConnectionContext, card: CardTrait, terminalState: TerminalState) =
    terminalState.transmissions.selectTransmission match {
      case Some(SelectTransmission(Some(select), Some(SelectResponse(Some(d), NormalProcessingNoFurtherQualification)))) =>
        processGetProcessingOptionsBasedOnFciTemplate(context, card, terminalState, d).
          map(terminalState.withGetProcessingOptions(_))
      case _ => Task.fail(new RuntimeException("Select was no processed succesfully"))
    }

  def processReadRecords(context: ConnectionContext, card: CardTrait, terminalState: TerminalState): Task[TerminalState] =
    terminalState.transmissions.gpoTransmission match {
      case Some(GPOTransmission(_, Some(GPOResponseFormat1(Some(format1), NormalProcessingNoFurtherQualification)))) =>
        processReadRecords(context, card, terminalState, format1.afl)
      case Some(GPOTransmission(_, Some(GPOResponseFormat2(Some(format2), NormalProcessingNoFurtherQualification)))) =>
        format2.constructedValue.getTag(ApplicationFileLocator.tag) match {
          case (Some(afl: ApplicationFileLocator)) => processReadRecords(context, card, terminalState, afl)
          case _ => Task.fail(new RuntimeException("No AFL"))
        }
      case _ => Task.fail(new RuntimeException("GPO was no processed succesfully"))
    }

  def processReadRecords(context: ConnectionContext, card: CardTrait, terminalState: TerminalState, afl: ApplicationFileLocator): Task[TerminalState] = {
    val aidOption = terminalState.transientData.candidateList.headOption
    val parser = terminalState.getBrandParser(aidOption)
    parser match {
      case Some(x) =>  readRecords(context, card, afl, x).map(terminalState.withReadRecordsTransmission(_))
      case None => Task.fail(new RuntimeException("no parser"))
    }
  }

  def processGetProcessingOptionsBasedOnFciTemplate(context: ConnectionContext, card: CardTrait,
                                                    terminalState: TerminalState,
                                                    fciTemplate: BerTLV) = {
    val aidOption = terminalState.transientData.candidateList.headOption
    val parser = terminalState.getBrandParser(aidOption)
    parser match {
      case Some(x) => for {
        pdol <- getPDOL(fciTemplate)
        trans <- performGPO(context, card, pdol, terminalState.terminalTLV(), x)
      } yield (trans)
      case None => Task.fail(new RuntimeException("no parser"))
    }

  }


  def getPDOL(fciTemplate: BerTLV): Task[Option[ProcessingOptionsDataObjectList]] =
    fciTemplate.select(List(PathEx(ProcessingOptionsDataObjectList.tag))) match {
      case Some((pdol: ProcessingOptionsDataObjectList) :: xs) => Task(Some(pdol))
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
            if (supportedAids.contains(a)) => List((a, v.number))
          case (Some(ApplicationDedicatedFileName(a)), _)
            if (supportedAids.contains(a)) => List((a, Int.MaxValue))
          case _ => Nil
        }
      }
      case _ => Nil
    })


}
