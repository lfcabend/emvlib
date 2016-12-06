package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultNumericWithLengthSpec, EMVTLVLeafNTextable}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/17/16.
  */
case class MerchantCategoryCode(override val value: Seq[Byte])
  extends EMVTLVLeafNTextable {

  override val tag: BerTag = MerchantCategoryCode.tag

}

object MerchantCategoryCode extends EMVDefaultNumericWithLengthSpec[MerchantCategoryCode] {

  val tag: BerTag = "9F15"

  val length: Int = 2

  override val max: Int = 4

  override val min: Int = 4

}
