package org.emv.commands

import org.emv.tlv.EMVTLV.EMVTLVType
import org.emv.tlv.{READRECORDResponseMessageTemplate, ResponseMessageTemplateFormat2}
import org.iso7816.APDU.APDUCommandResponse
import org.iso7816.{StatusWord, StatusWordT}

/**
  * Created by lau on 2/7/17.
  */
case class ReadRecordResponse(val readRecordTemplate: Option[READRECORDResponseMessageTemplate],
                              override val statusWord: StatusWordT)
  extends APDUCommandResponse(readRecordTemplate.map(_.value), statusWord)

object ReadRecordResponse {

  import fastparse.byte.all._

  def parser(parser: Parser[EMVTLVType]): Parser[ReadRecordResponse] = for {
    t <- (org.emv.tlv.READRECORDResponseMessageTemplate.parser(parser).?)
    st <- StatusWord.parseStatusWord
    _ <- fastparse.byte.all.End
  } yield (ReadRecordResponse(t, st))

}