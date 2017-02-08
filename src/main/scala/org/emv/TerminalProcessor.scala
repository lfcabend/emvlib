package org.emv

import javax.smartcardio.Card

import org.emv.tlv.EMVTLV.EMVTLVType
import org.emv.tlv._
import org.iso7816.{AID, NormalProcessingNoFurtherQualification, SelectResponse}
import org.lau.tlv.ber._
import scodec.bits._

import scala.collection.immutable.::
import scalaz.concurrent.Task

/**
  * Created by lau on 7/6/16.
  */
object TerminalProcessor {

  def performSelectPPSE(context: ConnectionContext, card: CardTrait, terminalState: TerminalState): Task[TerminalState] =
    EMVCardHandler.performSelect(context, card, AID.PPSE).map(terminalState.withSelectPPSE(_))


  def performSelectApplication(context: ConnectionContext, card: CardTrait,
                               terminalState: TerminalState): Task[TerminalState] = {

    val ppseTrans = terminalState.transmissions.selectPPSETransmission
    ppseTrans match {
      case Some(SelectTransmission(Some(selectCommand),
      Some(SelectResponse(Some(fciTemplate), NormalProcessingNoFurtherQualification)))) =>
        processSelectBasedOnFciTemplate(context, card, terminalState, fciTemplate)
      case _ => Task.fail(new RuntimeException("did not perform ppse succesfully"))
    }
  }


  def processSelectBasedOnFciTemplate(context: ConnectionContext, card: CardTrait, terminalState: TerminalState,
                                      fciTemplate: EMVTLVType) = fciTemplate -> (berTag"61") match {
    case Some(applicationTemplates) => processSelectBasedOnApplicationTemplates(context, card, terminalState, applicationTemplates)
    case _ => Task.fail(new RuntimeException("Could not find the application templates"))
  }

  def processGetProcessingOptions(context: ConnectionContext, card: CardTrait, terminalState: TerminalState) =
    terminalState.transmissions.selectTransmission match {
      case Some(SelectTransmission(Some(select), Some(SelectResponse(Some(d), NormalProcessingNoFurtherQualification)))) =>
        processGetProcessingOptionsBasedOnFciTemplate(context, card, terminalState, d)
      case _ => Task.fail(new RuntimeException("Select was no processed succussfully"))
    }

  def processGetProcessingOptionsBasedOnFciTemplate(context: ConnectionContext, card: CardTrait,
                                                    terminalState: TerminalState,
                                                    fciTemplate: BerTLV) =
    for {
      pdol <- getPDOL(fciTemplate)
      trans <- EMVCardHandler.performGPO(context, card, pdol, terminalState.terminalTLV())
    } yield (trans)


  def getPDOL(fciTemplate: BerTLV): Task[Option[ProcessingOptionsDataObjectList]] = fciTemplate.select(List(PathEx(ProcessingOptionsDataObjectList.tag))) match {
    case Some((pdol: ProcessingOptionsDataObjectList) :: xs) => Task(Some(pdol))
    case _ => Task(None)
  }

  def processSelectBasedOnApplicationTemplates(context: ConnectionContext,
                                               card: CardTrait, terminalState: TerminalState,
                                               applicationTemplates: List[BerTLV]) =
    getIntersectionBetweenTerminalAndCard(applicationTemplates, terminalState) match {
      case (x :: xs) => EMVCardHandler.performSelect(context, card, x).map(terminalState.withSelectApplication(_))
      case _ => Task.fail(new RuntimeException("Did not find any supported application"))
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
        val fileName = templateTags.getTag(DedicatedFileName.tag)
        val priority = templateTags.getTag(ApplicationPriorityIndicator.tag)
        (fileName, priority) match {
          case (Some(DedicatedFileName(a)), Some(v@ApplicationPriorityIndicator(_)))
            if (supportedAids.contains(a)) => List((a, v.number))
          case (Some(DedicatedFileName(a)), _)
            if (supportedAids.contains(a)) => List((a, Int.MaxValue))
          case _ => Nil
        }
      }
      case _ => Nil
    })


}
