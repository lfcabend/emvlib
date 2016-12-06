package org.emv

import javax.smartcardio.Card

import org.emv.tlv.EMVTLV.EMVParser
import org.iso7816.APDU.{APDUCommand, APDUCommandResponse}
import org.iso7816._

import scalaz.concurrent.Task

/**
  * Created by lau on 7/5/16.
  */
object EMVCardHandler {

  def performSelect(card: Card, aid: AID): Task[SelectTransmission] = {
    val selectCommand = org.iso7816.Select.selectDFFirstOccurenceWithFCIResponse(aid)
    for {
      response <- transmitEMVCommand(card, selectCommand, EMVParser.parseSelectResponse)
    } yield (new SelectTransmission(Some(selectCommand), Some(response)))
  }

  def transmitEMVCommand[T <: APDUCommandResponse](card: Card, apduCommand: APDUCommand,
                                                   parser: EMVParser.Parser[T]): Task[T] = for {
    response <- PCSCCard.transmit(card, apduCommand.serialize)
  } yield(tryToParseApduResponse(response, parser))

  def tryToParseApduResponse[T](in: Seq[Byte], parser: EMVParser.Parser[T]): T = {
    EMVParser.parse(parser, in) match {
      case EMVParser.Success(result, _) => result
      case EMVParser.NoSuccess(msg, _) => throw new ResponsParseError(msg)
    }
  }

}




class ResponsParseError(msg: String) extends Exception