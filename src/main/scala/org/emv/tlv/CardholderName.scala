package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultAlphaNumericSpecialWithLengthSpec, EMVDefaultAlphaNumericSpecialWithVarLengthSpec, EMVDefaultBinaryWithVarLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 6/17/16.
  */
case class CardholderName(override val value: Seq[Byte])
  extends EMVTLVLeaf {

  override val tag: BerTag = CardholderName.tag

}

object CardholderName extends EMVDefaultAlphaNumericSpecialWithVarLengthSpec[CardholderName] {

  val tag: BerTag = "5F20"

  override val maxLength: Int = 26

  override val minLength: Int = 2
  override val max: Int = 26
  override val min: Int = 2
}
