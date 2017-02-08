package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/30/16.
  */
case class CertificationAuthorityPublicKeyIndex(override val value: ByteVector)
  extends EMVTLVLeaf {

  override val tag: BerTag = CertificationAuthorityPublicKeyIndex.tag

}

object CertificationAuthorityPublicKeyIndex extends EMVDefaultBinaryWithLengthSpec[CertificationAuthorityPublicKeyIndex] {

  override val length: Int = 1

  override val tag: BerTag = berTag"8F"

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[CertificationAuthorityPublicKeyIndex] =
    parseEMVBySpec(CertificationAuthorityPublicKeyIndex, parseB(_))

}
