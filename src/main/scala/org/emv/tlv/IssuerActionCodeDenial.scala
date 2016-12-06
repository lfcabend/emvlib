package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/7/16.
  */
case class IssuerActionCodeDenial (override val value: Seq[Byte])
  extends EMVTLVLeaf {

  override val tag: BerTag = IssuerActionCodeDenial.tag

}

object IssuerActionCodeDenial extends EMVDefaultBinaryWithLengthSpec[IssuerActionCodeDenial] {

  val tag: BerTag = "9F0E"

  override val length: Int = 5

}