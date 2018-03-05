package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._


/**
  * Created by lau on 6/30/16.
  */
case class CertificationAuthorityPublicKeyIndexTerminal (override val value: ByteVector)
  extends EMVTLVLeaf {

  override val tag = CertificationAuthorityPublicKeyIndexTerminal.tag

}

object CertificationAuthorityPublicKeyIndexTerminal extends EMVDefaultBinaryWithLengthSpec[CertificationAuthorityPublicKeyIndexTerminal] {

  override val length = 1

  override val tag = berTag"9F22"

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(CertificationAuthorityPublicKeyIndexTerminal, parseB(_))

}
