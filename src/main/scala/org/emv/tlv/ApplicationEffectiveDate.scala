package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDateSpec, EMVTLVLeafWithDate, EMVTLVParser, LeafToStringHelper}
import org.joda.time.LocalDate
import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/4/16.
  */
case class ApplicationEffectiveDate(val date: LocalDate) extends EMVTLVLeafWithDate {

  override val tag: BerTag = ApplicationEffectiveDate.tag

}

object ApplicationEffectiveDate extends EMVDateSpec[ApplicationEffectiveDate] {

  val tag: BerTag = berTag"5F25"

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._


  def parser: Parser[ApplicationEffectiveDate] =
    parseEMVBySpec(ApplicationEffectiveDate, parseDate(_))

}