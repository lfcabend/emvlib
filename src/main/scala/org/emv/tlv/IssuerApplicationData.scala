package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithVarLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/7/16.
  */
case class IssuerApplicationData(override val value: Seq[Byte])
  extends EMVTLVLeaf {

  override val tag: BerTag = IssuerApplicationData.tag

}

object IssuerApplicationData extends EMVDefaultBinaryWithVarLengthSpec[IssuerApplicationData] {

  val tag: BerTag = "9F10"

  override val maxLength: Int = 32

  override val minLength: Int = 0

}
