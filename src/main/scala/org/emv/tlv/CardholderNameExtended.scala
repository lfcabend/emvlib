package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultAlphaNumericSpecialWithVarLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 6/17/16.
  */
case class CardholderNameExtended(override val value: Seq[Byte])
  extends EMVTLVLeaf {

  override val tag: BerTag = CardholderNameExtended.tag

}

object CardholderNameExtended extends EMVDefaultAlphaNumericSpecialWithVarLengthSpec[CardholderNameExtended] {

  val tag: BerTag = "9F0B"

  override val maxLength: Int = 45

  override val minLength: Int = 27
  override val max: Int = 45
  override val min: Int = 27
}
