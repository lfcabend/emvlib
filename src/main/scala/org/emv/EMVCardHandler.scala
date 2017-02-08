package org.emv

import org.emv.commands.{GPOCommand, GPOResponse, ReadRecordCommand, ReadRecordResponse}
import org.emv.tlv.{ApplicationFileLocator, ProcessingOptionsDataObjectList}
import org.iso7816.APDU.{APDUCommand, APDUCommandResponse}
import org.iso7816._
import org.lau.tlv.ber._
import scodec.bits._
import fastparse.byte.all._

import scalaz._
import Scalaz._
import scalaz.concurrent.Task

/**
  * Created by lau on 7/5/16.
  */
object EMVCardHandler {

  def performSelect(context: ConnectionContext, card: CardTrait, aid: AID): Task[SelectTransmission] = {
    val selectCommand = org.iso7816.Select.selectDFFirstOccurenceWithFCIResponse(aid)
    for {
      response <- transmitEMVCommand(context, card, selectCommand, SelectResponse.parser)
    } yield (new SelectTransmission(Some(selectCommand), Some(response)))
  }

  def performGPO(context: ConnectionContext, card: CardTrait, pdol: Option[ProcessingOptionsDataObjectList],
                 tlvList: List[BerTLV]): Task[GPOTransmission] = {
    val gpoCommand = pdol match {
      case Some(x) => GPOCommand(x, tlvList)
      case None => GPOCommand()
    }
    for {
      response <- transmitEMVCommand(context, card, gpoCommand, GPOResponse.parser)
    } yield (new GPOTransmission(Some(gpoCommand), Some(response)))
  }

  def readRecords(context: ConnectionContext, card: CardTrait, afl: ApplicationFileLocator): Task[Seq[ReadRecordTransmission]] =
    afl.allRecords.map({
      case (sfi, record) => readRecord(context, card, sfi, record)
    }).sequence[Task, ReadRecordTransmission]

  def readRecord(context: ConnectionContext, card: CardTrait,
                 sfi: Byte, record: Byte): Task[ReadRecordTransmission] = {
    val readRecordCommand = ReadRecordCommand(record, sfi)
    for {
      response <- transmitEMVCommand(context, card, readRecordCommand, ReadRecordResponse.parser)
    } yield (new ReadRecordTransmission(Some(readRecordCommand), Some(response)))
  }

  def transmitEMVCommand[T <: APDUCommandResponse](context: ConnectionContext, card: CardTrait,
                                                   apduCommand: APDUCommand,
                                                   parser: Parser[T]): Task[T] = for {
    response <- card.transmit(context, apduCommand.serialize)
  } yield (tryToParseApduResponse(response, parser))


  def tryToParseApduResponse[T](in: ByteVector, parser: Parser[T]): T = {
    parser.parse(in) match {
      case Parsed.Success(result, _) => result
      case Parsed.Failure(lp, index, r3) =>
        throw new ResponsParseError(s"${lp}\n${index}\n${r3}")
    }
  }

}

case class ResponsParseError(detailMessage: String) extends Exception {

  override def toString: String = detailMessage

}