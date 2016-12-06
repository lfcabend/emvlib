package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/7/16.
  */
case class IssuerActionCodeOnline(override val value: Seq[Byte])
  extends EMVTLVLeaf {

  override val tag: BerTag = IssuerActionCodeOnline.tag

}

object IssuerActionCodeOnline extends EMVDefaultBinaryWithLengthSpec[IssuerActionCodeOnline] {

  val tag: BerTag = "9F0F"

  override val length: Int = 5

}

