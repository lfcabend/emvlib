package org.emv.tlv

import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat

import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/4/16.
  */
trait DateHelper {

  override def toString: String =
    s"""${super.toString}
       |\t${DateHelper.formatterPretty.print(date)}
     """.stripMargin

  val value: ByteVector = DateHelper.date2Bytes(date)

  val date: LocalDate


}

object DateHelper {

  val formatter = DateTimeFormat.forPattern("yyMMdd")

  val formatterPretty = DateTimeFormat.longDate()

  def date2Bytes(date: LocalDate): ByteVector = ByteVector.fromValidHex(formatter.print(date))

  def bytesToDate(bytes: ByteVector): LocalDate = formatter.parseLocalDate(bytes.toHex)

}
