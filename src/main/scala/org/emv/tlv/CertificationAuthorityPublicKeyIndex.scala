package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 6/30/16.
  */
case class CertificationAuthorityPublicKeyIndex(override val value: Seq[Byte])
  extends EMVTLVLeaf {

  override val tag: BerTag = CertificationAuthorityPublicKeyIndex.tag

}

object CertificationAuthorityPublicKeyIndex extends EMVDefaultBinaryWithLengthSpec[CertificationAuthorityPublicKeyIndex] {

  override val length: Int = 1

  override val tag: BerTag = "8F"

}
