package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithVarLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/10/16.
  */
case class IssuerPublicKeyRemainder (override val value: Seq[Byte])
  extends EMVTLVLeaf {

  override val tag: BerTag = IssuerPublicKeyExponent.tag

}

object IssuerPublicKeyRemainder extends EMVDefaultBinaryWithVarLengthSpec[IssuerPublicKeyRemainder] {

  val tag: BerTag = "92"

  override val maxLength: Int = 255

  override val minLength: Int = 0

}

