package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/7/16.
  */
case class IssuerActionCodeDefault(override val value: Seq[Byte])
  extends EMVTLVLeaf {

  override val tag: BerTag = IssuerActionCodeDefault.tag

}

object IssuerActionCodeDefault extends EMVDefaultBinaryWithLengthSpec[IssuerActionCodeDefault] {

  val tag: BerTag = "9F0D"

  override val length: Int = 5

}
