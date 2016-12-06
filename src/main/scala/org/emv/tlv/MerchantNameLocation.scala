package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVAlphaNumericSpecialWithVarLengthSpec, EMVTLVLeafTextable}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/28/16.
  */
case class MerchantNameLocation(override val value: Seq[Byte]) extends EMVTLVLeafTextable {

  override val tag: BerTag = MerchantNameLocation.tag

}

object MerchantNameLocation extends EMVAlphaNumericSpecialWithVarLengthSpec[Seq[Byte], MerchantNameLocation] {

  val tag: BerTag = "9F4E"

  override val maxLength: Int = Int.MaxValue

  override val minLength: Int = 0
  override val max: Int = Int.MaxValue
  override val min: Int = 0
}

