package org.emv.tlv

import org.joda.time.{LocalDate, LocalTime}
import org.joda.time.format.DateTimeFormat
import scodec.bits.ByteVector

/**
  * Created by lau on 2/17/17.
  */
trait TimeHelper {

  override def toString: String =
    s"""${super.toString}
       |\t${DateHelper.formatterPretty.print(time)}
     """.stripMargin

  val value: ByteVector = TimeHelper.time2Bytes(time)

  val time: LocalTime


}

object TimeHelper {

  val formatter = DateTimeFormat.forPattern("HHmmss")

  val formatterPretty = DateTimeFormat.longTime()

  def time2Bytes(date: LocalTime): ByteVector = ByteVector.fromValidHex(formatter.print(date))

  def bytesToTime(bytes: ByteVector): LocalTime = formatter.parseLocalTime(bytes.toHex)

}

