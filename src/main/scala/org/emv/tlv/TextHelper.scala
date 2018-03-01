package org.emv.tlv

import java.nio.charset.{Charset, CharsetEncoder}

import org.lau.tlv.ber._
import scodec.bits._

trait Textable {

  val value: ByteVector

  def text = TextHelper.text(value)

  override def toString: String =
    s"""${super.toString}
       |\t${text}""".stripMargin

}

trait NumberTextable {

  val value: ByteVector

  def text = TextHelper.nToText(value)

}


trait CompactNumberTextable {

  val value: ByteVector

  def text = TextHelper.cnToText(value)

}

/**
  * Created by lau on 6/7/16.
  */
object TextHelper {

  def text(value: ByteVector) = new String(value.toArray, "US-ASCII")

  def textToBytes(value: String) = ByteVector(value.getBytes(Charset.forName("US-ASCII")))

  def nToText(value: ByteVector) = value.toHex

  def cnToText(value: ByteVector) = value.toHex.replaceAll("F", "")

  val asciiEncoder: CharsetEncoder = Charset.forName("US-ASCII").newEncoder()

  def isPureAscii(v: String) = asciiEncoder.canEncode(v)

  def isPureAscii(v: ByteVector) = asciiEncoder.canEncode(text(v))
}
