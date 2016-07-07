package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVBinaryWithLengthSpec, EMVBinaryWithVarLengthSpec, EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 6/27/16.
  */
case class CardholderVerificationMethodResults(override val value: Seq[Byte]) extends EMVTLVLeaf {

  override val tag: BerTag = CardholderVerificationMethodResults.tag

}

object CardholderVerificationMethodResults extends EMVDefaultBinaryWithLengthSpec[CardholderVerificationMethodResults] {

  override val tag: BerTag = "9F34"

  override val length: Int = 3

}