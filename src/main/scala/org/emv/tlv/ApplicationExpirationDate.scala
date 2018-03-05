package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.joda.time.LocalDate
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/4/16.
  */
case class ApplicationExpirationDate(val date: LocalDate)
  extends EMVTLVLeafWithDate with TemplateTag {

  override val tag = ApplicationExpirationDate.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)
}

object ApplicationExpirationDate extends EMVDateSpec[ApplicationExpirationDate] {

  val tag = berTag"5F24"


  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(ApplicationExpirationDate, parseDate(_))

}
