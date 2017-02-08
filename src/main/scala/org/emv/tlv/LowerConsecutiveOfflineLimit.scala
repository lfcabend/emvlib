package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/17/16.
  */
case class LowerConsecutiveOfflineLimit(override val value: ByteVector) extends EMVTLVLeaf {

  override val tag: BerTag = LowerConsecutiveOfflineLimit.tag

}

object LowerConsecutiveOfflineLimit extends EMVDefaultBinaryWithLengthSpec[LowerConsecutiveOfflineLimit] {

  val tag: BerTag = berTag"9F14"

  override val length: Int = 1

  def parser: Parser[LowerConsecutiveOfflineLimit] =
    parseEMVBySpec(LowerConsecutiveOfflineLimit, parseB(_))
}