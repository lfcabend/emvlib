package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithVarLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/7/16.
  */
case class IssuerAuthenticationData(override val value: Seq[Byte])
  extends EMVTLVLeaf {

  override val tag: BerTag = IssuerAuthenticationData.tag

}

object IssuerAuthenticationData extends EMVDefaultBinaryWithVarLengthSpec[IssuerAuthenticationData] {

  val tag: BerTag = "91"

  override val maxLength: Int = 16

  override val minLength: Int = 8

}
