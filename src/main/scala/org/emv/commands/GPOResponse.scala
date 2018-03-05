package org.emv.commands

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVType
import org.emv.tlv.{GPOResponseMessageTemplateFormat1, ResponseMessageTemplateFormat2}
import org.iso7816.APDU.APDUCommandResponse
import org.iso7816.{SelectResponse, StatusWord, StatusWordT}

/**
  * Created by lau on 12/13/16.
  */
trait GPOResponse extends APDUCommandResponse

case class GPOResponseFormat1(val format1: Option[GPOResponseMessageTemplateFormat1],
                              override val statusWord: StatusWordT)
  extends APDUCommandResponse(format1.map(_.value), statusWord) with GPOResponse


case class GPOResponseFormat2(val format2: Option[ResponseMessageTemplateFormat2],
                              override val statusWord: StatusWordT)
  extends APDUCommandResponse(format2.map(_.value), statusWord) with GPOResponse

object GPOResponse {

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser(parser: Parser[EMVTLVType]) = P(gpoResponseParserFormat1 | gpoResponseParserFormat2(parser))

  def gpoResponseParserFormat1 :Parser[GPOResponseFormat1] = for {
    t <- (GPOResponseMessageTemplateFormat1.parseGPOResponseMessageTemplateFormat1.?)
    st <- StatusWord.parseStatusWord
    _ <- fastparse.byte.all.End
  } yield (GPOResponseFormat1(t, st))


  def gpoResponseParserFormat2(parser: Parser[EMVTLVType]) :Parser[GPOResponseFormat2] = for {
    t <- (ResponseMessageTemplateFormat2.parser(parser).?)
    st <- StatusWord.parseStatusWord
    _ <- fastparse.byte.all.End
  } yield (GPOResponseFormat2(t, st))

}