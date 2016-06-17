package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDateSpec, EMVTLVLeafWithDate, LeafToStringHelper}
import org.joda.time.LocalDate
import org.joda.time.format.{DateTimeFormatter, DateTimeFormat}
import org.tlv.HexUtils
import org.tlv.TLV.{BerTag, BerTLVLeafT}

/**
  * Created by lau on 6/4/16.
  */
case class ApplicationEffectiveDate(val date: LocalDate) extends EMVTLVLeafWithDate {

  override val tag: BerTag = ApplicationEffectiveDate.tag

}

object ApplicationEffectiveDate extends EMVDateSpec[ApplicationEffectiveDate] {

  val tag: BerTag = "5F25"

}