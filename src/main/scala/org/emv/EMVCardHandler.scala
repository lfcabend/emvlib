package org.emv

import com.typesafe.scalalogging.LazyLogging
import org.emv.commands._
import org.emv.tlv._
import org.iso7816.APDU.{APDUCommand, APDUCommandResponse}
import org.iso7816._
import org.lau.tlv.ber._
import scodec.bits._
import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVType

import scalaz._
import Scalaz._
import scalaz.concurrent.Task

/**
  * Created by lau on 7/5/16.
  */
object EMVCardHandler extends LazyLogging {

  def performSelect(context: ConnectionContext, card: CardTrait, aid: AID,
                    parser: Parser[EMVTLVType] = EMVTLV.EMVTLVParser.parseEMVTLV): Task[SelectTransmission] = {
    val selectCommand = org.iso7816.Select.selectDFFirstOccurenceWithFCIResponse(aid)
    for {
      _ <- Task { logger.debug(s"select AID: ${selectCommand.aid}")}
      response <- transmitEMVCommand(context, card, selectCommand, SelectResponse.parser(parser))
      _ <- Task { logger.debug(s"select response: ${response}")}
    } yield (new SelectTransmission(Some(selectCommand), Some(response)))
  }

  def performGPO(context: ConnectionContext, card: CardTrait, pdol: Option[ProcessingOptionsDataObjectList],
                 tlvList: List[BerTLV], parser: Parser[EMVTLVType] = EMVTLV.EMVTLVParser.parseEMVTLV): Task[GPOTransmission] = {
    val gpoCommand = pdol match {
      case Some(x) => GPOCommand(x, tlvList)
      case None => GPOCommand()
    }
    for {
      _ <- Task { logger.debug(s"GPO with data: ${gpoCommand.commandTemplate}") }
      response <- transmitEMVCommand(context, card, gpoCommand, GPOResponse.parser(parser))
      _ <- Task { logger.debug(s"GPO response: ${response}")}
    } yield (new GPOTransmission(Some(gpoCommand), Some(response)))
  }

  def readRecords(context: ConnectionContext, card: CardTrait, afl: ApplicationFileLocator,
                  parser: Parser[EMVTLVType] = EMVTLV.EMVTLVParser.parseEMVTLV): Task[List[ReadRecordTransmission]] = {

    def readRecordsRec(context: ConnectionContext, card: CardTrait, aflEnrties: List[(Byte, Byte)],
                       readRecordTransmissions: List[ReadRecordTransmission]): Task[List[ReadRecordTransmission]] =
      aflEnrties match {
        //take the first and read it
        case x :: cs => readRecord(context, card, x._1, x._2, parser).
          flatMap({
            //if successful then read more
            case r1@ReadRecordTransmission(rrc, Some(ReadRecordResponse(_, NormalProcessingNoFurtherQualification))) => {
              readRecordsRec(context, card, cs, readRecordTransmissions ++ List(r1))
            }
            //if not then return was was read with the last one
            case r2@_ => Task(readRecordTransmissions ++ List(r2))
          })
        //no records to read anymore return wat we read
        case Nil => Task(readRecordTransmissions)
      }

    readRecordsRec(context, card, afl.allRecords, List())
  }


  def readRecord(context: ConnectionContext, card: CardTrait,
                 sfi: Byte, record: Byte,
                 parser: Parser[EMVTLVType] = EMVTLV.EMVTLVParser.parseEMVTLV): Task[ReadRecordTransmission] = {
    val readRecordCommand = ReadRecordCommand(record, sfi)
    for {
      _ <- Task { logger.debug(s"Read record, file: ${readRecordCommand.sfi}, record: ${readRecordCommand.recordNumber}") }
      response <- transmitEMVCommand(context, card, readRecordCommand, ReadRecordResponse.parser(parser))
      _ <- Task { logger.debug(s"Read record response: ${response}") }
    } yield (new ReadRecordTransmission(Some(readRecordCommand), Some(response)))
  }

  def generateAC(context: ConnectionContext, card: CardTrait, cid: CryptogramInformationData,
                 cdol: CardRiskManagementDataObjectList1, tlvList: List[BerTLV],
                 cda: Boolean = false,
                 parser: Parser[EMVTLVType] = EMVTLV.EMVTLVParser.parseEMVTLV): Task[GenerateACTransmission] = {
    val generateACCommand = GenerateACCommand(cid, cdol, tlvList, cda)
    for {
      _ <- Task { logger.debug(s"Generate ac: ${generateACCommand.commandTemplate}") }
      response <- transmitEMVCommand(context, card, generateACCommand, GenerateACResponse.parser(parser))
      _ <- Task { logger.debug(s"Generate AC response: ${response}") }
    } yield (new GenerateACTransmission(Some(generateACCommand), Some(response)))
  }

  def transmitEMVCommand[T <: APDUCommandResponse](context: ConnectionContext, card: CardTrait,
                                                   apduCommand: APDUCommand,
                                                   parser: Parser[T]): Task[T] = for {
    response <- card.transmit(context, apduCommand.serialize)
  } yield tryToParseApduResponse(response, parser)


  def tryToParseApduResponse[T](in: ByteVector, parser: Parser[T]): T = {
    parser.parse(in) match {
      case Parsed.Success(result, _) => result
      case Parsed.Failure(lp, index, r3) =>
        throw new ResponsParseError(s"parser ERRor: ${lp}\n${index}\n${r3}")
    }
  }

}

case class ResponsParseError(detailMessage: String) extends Exception {

  override def toString: String = detailMessage

}