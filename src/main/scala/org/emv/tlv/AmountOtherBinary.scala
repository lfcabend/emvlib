package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVBinaryWithLengthSpec, EMVTLVLeaf, LeafToStringHelper}
import org.tlv.TLV.{BerTag, BerTLVLeafT}

/**
  * Created by lau on 6/1/16.
  */
case class AmountOtherBinary(override val value: Seq[Byte]) extends EMVTLVLeaf {

  require(value.length == AmountOtherBinary.length)

  override val tag: BerTag = AmountOtherBinary.tag

}

object AmountOtherBinary extends EMVDefaultBinaryWithLengthSpec[AmountOtherBinary] {

  val length = 4

  val tag: BerTag = "9F04"

}


