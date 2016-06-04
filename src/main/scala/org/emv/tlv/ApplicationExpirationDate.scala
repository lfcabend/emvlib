package org.emv.tlv

import org.emv.tlv.EMVTLV.LeafToStringHelper
import org.joda.time.LocalDate
import org.tlv.TLV.{BerTag, BerTLVLeafT}

/**
  * Created by lau on 6/4/16.
  */
case class ApplicationExpirationDate(override val value: Seq[Byte])
  extends BerTLVLeafT with LeafToStringHelper  with DateHelper {

  def this(date: LocalDate) = this(DateHelper.date2Bytes(date))

  override def tag(): BerTag = ApplicationExpirationDate.tag


}

object ApplicationExpirationDate {

  def tag: BerTag = "5F24"

  def length: Int = 3

  def max: Int = 6

  def min: Int = 6

}
