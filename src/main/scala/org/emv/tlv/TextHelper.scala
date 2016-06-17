package org.emv.tlv

import org.tlv.HexUtils

trait Textable {

  val value: Seq[Byte]

  def text = TextHelper.text(value)

}

trait NumberTextable {

  val value: Seq[Byte]

  def text = TextHelper.nToText(value)

}


trait CompactNumberTextable {

  val value: Seq[Byte]

  def text = TextHelper.cnToText(value)

}

/**
  * Created by lau on 6/7/16.
  */
object TextHelper {

  def text(value: Seq[Byte]) = new String(value.toArray, "ASCII")

  def nToText(value: Seq[Byte]) = HexUtils.toHex(value)

  def cnToText(value: Seq[Byte]) = HexUtils.toHex(value).replaceAll("F", "")

}
