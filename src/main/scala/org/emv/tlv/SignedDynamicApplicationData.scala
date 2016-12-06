package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithVarLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/28/16.
  */
case class SignedDynamicApplicationData(override val value: Seq[Byte]) extends EMVTLVLeaf {

  override val tag: BerTag = SignedDynamicApplicationData.tag

}

object SignedDynamicApplicationData extends EMVDefaultBinaryWithVarLengthSpec[SignedDynamicApplicationData] {

  val tag: BerTag = "9F4B"

  override val maxLength: Int = 255

  override val minLength: Int = 0

}