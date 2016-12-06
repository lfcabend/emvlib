package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithVarLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/10/16.
  */
case class IssuerPublicKeyExponent  (override val value: Seq[Byte])
  extends EMVTLVLeaf {

  override val tag: BerTag = IssuerPublicKeyExponent.tag

}

object IssuerPublicKeyExponent extends EMVDefaultBinaryWithVarLengthSpec[IssuerPublicKeyExponent] {

  val tag: BerTag = "9F32"

  override val maxLength: Int = 3

  override val minLength: Int = 1

}


