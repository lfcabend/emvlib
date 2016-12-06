package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/17/16.
  */
case class LowerConsecutiveOfflineLimit(override val value: Seq[Byte]) extends EMVTLVLeaf {

  override val tag: BerTag = LowerConsecutiveOfflineLimit.tag

}

object LowerConsecutiveOfflineLimit extends EMVDefaultBinaryWithLengthSpec[LowerConsecutiveOfflineLimit] {

  val tag: BerTag = "9F14"

  override val length: Int = 1

}