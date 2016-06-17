package org.emv.tlv

/**
  * Created by lau on 6/7/16.
  */
trait BinaryNumber {

  def value: Seq[Byte]

  def number: Int = NumberHelper.fromBinary(value)

}

trait TextableNumber {

  def value: Seq[Byte]

  def text: String

  def number: Int = NumberHelper.fromText(text)

}

object NumberHelper {

  def fromBinary(value: Seq[Byte]): Int = BigInt(1, value.toArray).intValue()

  def fromText(text: String): Int = text.toInt

}
