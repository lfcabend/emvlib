package org.emv.commands

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

  def parser: Parser[ReadRecordResponse] = for {
    t <- (org.emv.tlv.READRECORDResponseMessageTemplate.parser.?)
    st <- StatusWord.parseStatusWord
    _ <- fastparse.byte.all.End
  } yield (ReadRecordResponse(t, st))

}