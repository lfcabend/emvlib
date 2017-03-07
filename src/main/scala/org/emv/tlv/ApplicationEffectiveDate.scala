package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.joda.time.LocalDate
import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/4/16.
  */
case class ApplicationEffectiveDate(val date: LocalDate)
  extends EMVTLVLeafWithDate with TemplateTag {

  override val tag = ApplicationEffectiveDate.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)
}

object ApplicationEffectiveDate extends EMVDateSpec[ApplicationEffectiveDate] {

  val tag = berTag"5F25"

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._


  def parser = parseEMVBySpec(ApplicationEffectiveDate, parseDate(_))

}