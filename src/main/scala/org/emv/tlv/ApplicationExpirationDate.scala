package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDateSpec, EMVTLVLeafWithDate, LeafToStringHelper}
import org.joda.time.LocalDate
import org.tlv.TLV.{BerTag, BerTLVLeafT}

/**
  * Created by lau on 6/4/16.
  */
case class ApplicationExpirationDate(val date: LocalDate) extends EMVTLVLeafWithDate {

  override val tag: BerTag = ApplicationExpirationDate.tag

}

object ApplicationExpirationDate extends EMVDateSpec[ApplicationExpirationDate] {

  val tag: BerTag = "5F24"

}
