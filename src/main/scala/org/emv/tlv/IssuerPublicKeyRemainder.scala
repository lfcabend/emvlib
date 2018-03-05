package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/10/16.
  */
case class IssuerPublicKeyRemainder (override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = IssuerPublicKeyExponent.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)
}

object IssuerPublicKeyRemainder extends EMVDefaultBinaryWithVarLengthSpec[IssuerPublicKeyRemainder] {

  val tag = berTag"92"

  override val maxLength = 255

  override val minLength = 0

  def parser = parseEMVBySpec(IssuerPublicKeyRemainder, parseB(_))

}

