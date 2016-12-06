package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVAlphaNumericSpecialWithLengthSpec, EMVTLVLeafTextable}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/28/16.
  */
case class MerchantIdentifier(override val value: Seq[Byte]) extends EMVTLVLeafTextable {

  override val tag: BerTag = MerchantIdentifier.tag

}

object MerchantIdentifier extends EMVAlphaNumericSpecialWithLengthSpec[Seq[Byte], MerchantIdentifier] {

  val tag: BerTag = "9F16"
  override val length: Int = 15
  override val max: Int = 15
  override val min: Int = 15
}

