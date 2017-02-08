package org.emv.tlv

import org.lau.tlv.ber._
import scodec.bits._

trait Textable {

  val value: ByteVector

  def text = TextHelper.text(value)

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

  def text(value: ByteVector) = new String(value.toArray, "ASCII")

  def nToText(value: ByteVector) = value.toHex

  def cnToText(value: ByteVector) = value.toHex.replaceAll("F", "")

}
