package org.emv.tlv

import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.tlv.HexUtils

/**
  * Created by lau on 6/4/16.
  */
trait DateHelper {

  override def toString: String =
    s"""${super.toString}
       |\t${DateHelper.formatterPretty.print(date)}
     """.stripMargin

  val value: Seq[Byte] = DateHelper.date2Bytes(date)

  val date: LocalDate


}

object DateHelper {

  val formatter = DateTimeFormat.forPattern("yyMMdd")

  val formatterPretty = DateTimeFormat.longDate()

  def date2Bytes(date: LocalDate): Seq[Byte] = HexUtils.hex2Bytes(formatter.print(date))

  def bytesToDate(bytes: Seq[Byte]): LocalDate = formatter.parseLocalDate(HexUtils.toHex(bytes))

}
