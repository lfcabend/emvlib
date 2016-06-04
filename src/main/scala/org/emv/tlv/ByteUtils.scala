package org.emv.tlv

/**
  * Created by lau on 5/31/16.
  */
object ByteUtils {

  def withBitInByteSet(value: Seq[Byte], bitIndex: Int, byteIndex: Int): Seq[Byte] =
    if (byteIndex >= 1 && byteIndex <= value.size) {
      val bit2Set = 1 << bitIndex - 1
      val u = (value(byteIndex - 1) | bit2Set).toByte
      value.updated(byteIndex - 1, u)
    } else value

  def withBitInByteUnSet(value: Seq[Byte], bitIndex: Int, byteIndex: Int): Seq[Byte] =
    if (byteIndex >= 1 && byteIndex <= value.size) {
      val bit2Set = 1 << bitIndex - 1
      val u = (value(byteIndex - 1) & ~bit2Set).toByte
      value.updated(byteIndex - 1, u)
    } else value

  def isBitSetInByte(value: Seq[Byte], bitIndex: Int, byteIndex: Int): Boolean =
    if (byteIndex >= 1 && byteIndex <= value.size) {
      val bit2Check = 1 << bitIndex - 1
      (value(byteIndex - 1) & bit2Check) == bit2Check
    } else false
}
