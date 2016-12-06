package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithVarLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/10/16.
  */
// todo should improve the certificate
case class IssuerPublicKeyCertificate (override val value: Seq[Byte])
  extends EMVTLVLeaf {

  override val tag: BerTag = IssuerPublicKeyCertificate.tag

}

object IssuerPublicKeyCertificate extends EMVDefaultBinaryWithVarLengthSpec[IssuerPublicKeyCertificate] {

  val tag: BerTag = "90"

  //todo
  override val maxLength: Int = 255
  override val minLength: Int = 0
}
