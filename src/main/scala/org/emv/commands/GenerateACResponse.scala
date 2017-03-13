package org.emv.commands

import org.emv.tlv.EMVTLV.EMVTLVType
import org.emv.tlv.{GPOResponseMessageTemplateFormat1, GenerateACResponseMessageTemplateFormat1, ResponseMessageTemplateFormat2}
import org.iso7816.APDU.APDUCommandResponse
import org.iso7816.{StatusWord, StatusWordT}

trait GenerateACResponse extends APDUCommandResponse


case class GenerateACResponseFormat1(val format1: Option[GenerateACResponseMessageTemplateFormat1],
                                     override val statusWord: StatusWordT)
  extends APDUCommandResponse(format1.map(_.value), statusWord) with GPOResponse


case class GenerateACResponseFormat2(val format2: Option[ResponseMessageTemplateFormat2],
                                     override val statusWord: StatusWordT)
  extends APDUCommandResponse(format2.map(_.value), statusWord) with GPOResponse

object GenerateACResponse {

  import fastparse.byte.all._

  def parser(parser: Parser[EMVTLVType]): Parser[GenerateACResponse] = ???
//    for {
//    t <- (org.emv.tlv.READRECORDResponseMessageTemplate.parser.?)
//    st <- StatusWord.parseStatusWord
//    _ <- fastparse.byte.all.End
//  } yield (ReadRecordResponse(t, st))

}