package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/10/16.
  */
case class IssuerPublicKeyExponent  (override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag: BerTag = IssuerPublicKeyExponent.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)
}

object IssuerPublicKeyExponent extends EMVDefaultBinaryWithVarLengthSpec[IssuerPublicKeyExponent] {

  val tag = berTag"9F32"

  override val maxLength = 3

  override val minLength = 1

  def parser = parseEMVBySpec(IssuerPublicKeyExponent, parseB(_))

}


