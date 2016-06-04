package org.emv.tlv

import org.emv.tlv.EMVTLV.LeafToStringHelper
import org.joda.time.LocalDate
import org.joda.time.format.{DateTimeFormatter, DateTimeFormat}
import org.tlv.HexUtils
import org.tlv.TLV.{BerTag, BerTLVLeafT}

/**
  * Created by lau on 6/4/16.
  */
case class ApplicationEffectiveDate(override val value: Seq[Byte])
  extends BerTLVLeafT with LeafToStringHelper with DateHelper {

  require(date != null)

  def this(date: LocalDate) = this(DateHelper.date2Bytes(date))

  override def tag(): BerTag = ApplicationEffectiveDate.tag

}

object ApplicationEffectiveDate {

  def tag: BerTag = "5F25"

  def max: Int = 6

  def min: Int = 6

  def length: Int = 3

}