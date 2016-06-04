package org.emv.tlv

import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.tlv.HexUtils

/**
  * Created by lau on 6/4/16.
  */
trait DateHelper {

  def value: Seq[Byte]

  val date: LocalDate = DateHelper.formatter.parseLocalDate(HexUtils.toHex(value))

}

object DateHelper {

  val formatter = DateTimeFormat.forPattern("yyMMdd")

  def date2Bytes(date: LocalDate): Seq[Byte] = HexUtils.hex2Bytes(formatter.print(date))

}
