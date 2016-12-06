package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithVarLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/7/16.
  */
case class ICCDynamicNumber(override val value: Seq[Byte]) extends EMVTLVLeaf() {

  override val tag: BerTag = ICCDynamicNumber.tag

}

object ICCDynamicNumber extends EMVDefaultBinaryWithVarLengthSpec[ICCDynamicNumber] {

  override val maxLength = 8

  override val minLength = 2

  val tag: BerTag = "9F4C"

}
