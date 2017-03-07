package org.emv.tlv

import org.emv.tlv.EMVTLV._
import scodec.bits._
import org.lau.tlv.ber._

case class IntegratedCircuitCardPublicKeyExponent(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = IntegratedCircuitCardPublicKeyExponent.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)
}

object IntegratedCircuitCardPublicKeyExponent
  extends EMVDefaultBinaryWithVarLengthSpec[IntegratedCircuitCardPublicKeyExponent] {

  override val maxLength = 3

  override val minLength = 1

  val tag  = berTag"9F47"


  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(IntegratedCircuitCardPublicKeyExponent, parseB(_))

}