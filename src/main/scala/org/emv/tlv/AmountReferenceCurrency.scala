package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVBinaryWithLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.{BerTag, BerTLVLeafT}

/**
  * Created by lau on 6/2/16.
  */
case class AmountReferenceCurrency(override val value: Seq[Byte]) extends EMVTLVLeaf {

  override val tag: BerTag = AmountReferenceCurrency.tag

}

object AmountReferenceCurrency extends EMVDefaultBinaryWithLengthSpec[AmountReferenceCurrency] {

  val tag: BerTag = "9F3A"

  val length: Int = 4

}