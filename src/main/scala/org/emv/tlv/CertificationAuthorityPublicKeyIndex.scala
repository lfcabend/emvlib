package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/30/16.
  */
case class CertificationAuthorityPublicKeyIndex(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = CertificationAuthorityPublicKeyIndex.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)

}

object CertificationAuthorityPublicKeyIndex extends EMVDefaultBinaryWithLengthSpec[CertificationAuthorityPublicKeyIndex] {

  override val length = 1

  override val tag = berTag"8F"

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(CertificationAuthorityPublicKeyIndex, parseB(_))

}
