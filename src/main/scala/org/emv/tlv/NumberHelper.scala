package org.emv.tlv

import scodec.bits.ByteVector

/**
  * Created by lau on 6/7/16.
  */
trait BinaryNumber {

  def value: ByteVector

  def number: Int = NumberHelper.fromBinary(value)

}

trait TextableNumber {

  def value: ByteVector

  def text: String

  def number: Int = NumberHelper.fromText(text)

}

object NumberHelper {

  def fromBinary(value: ByteVector): Int = BigInt(1, value.toArray).intValue()

  def fromText(text: String): Int = text.toInt

}
