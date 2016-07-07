package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 6/30/16.
  */
case class CertificationAuthorityPublicKeyIndexTerminal (override val value: Seq[Byte])
  extends EMVTLVLeaf {

  override val tag: BerTag = CertificationAuthorityPublicKeyIndexTerminal.tag

}

object CertificationAuthorityPublicKeyIndexTerminal extends EMVDefaultBinaryWithLengthSpec[CertificationAuthorityPublicKeyIndexTerminal] {

  override val length: Int = 1

  override val tag: BerTag = "9F22"

}
