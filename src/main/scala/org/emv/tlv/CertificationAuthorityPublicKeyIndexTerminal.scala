package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._


/**
  * Created by lau on 6/30/16.
  */
case class CertificationAuthorityPublicKeyIndexTerminal (override val value: ByteVector)
  extends EMVTLVLeaf {

  override val tag: BerTag = CertificationAuthorityPublicKeyIndexTerminal.tag

}

object CertificationAuthorityPublicKeyIndexTerminal extends EMVDefaultBinaryWithLengthSpec[CertificationAuthorityPublicKeyIndexTerminal] {

  override val length: Int = 1

  override val tag: BerTag = berTag"9F22"

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[CertificationAuthorityPublicKeyIndexTerminal] =
        parseEMVBySpec(CertificationAuthorityPublicKeyIndexTerminal, parseB(_))

}
