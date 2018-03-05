package org.emv.tlv

import scodec.bits.ByteVector

/**
  * Created by lau on 5/31/16.
  */
object ByteUtils {

  def withBitInByteSet(value: ByteVector, bitIndex: Int, byteIndex: Int): ByteVector =
    if (byteIndex >= 1 && byteIndex <= value.size) {
      val bit2Set = 1 << bitIndex - 1
      val u = (value(byteIndex - 1) | bit2Set).toByte
      value.update(byteIndex - 1, u)
    } else value

  def withBitInByteUnSet(value: ByteVector, bitIndex: Int, byteIndex: Int): ByteVector =
    if (byteIndex >= 1 && byteIndex <= value.size) {
      val bit2Set = 1 << bitIndex - 1
      val u = (value(byteIndex - 1) & ~bit2Set).toByte
      value.update(byteIndex - 1, u)
    } else value

  def isBitSetInByte(value: ByteVector, bitIndex: Int, byteIndex: Int): Boolean =
    if (byteIndex >= 1 && byteIndex <= value.size) {
      val bit2Check = 1 << bitIndex - 1
      (value(byteIndex - 1) & bit2Check) == bit2Check
    } else false
}
