package org.emv.tlv

import org.emv.tlv.EMVTLV._
import scodec.bits._
import org.lau.tlv.ber._

case class IntegratedCircuitCardPublicKeyRemainder(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = IntegratedCircuitCardPublicKeyRemainder.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)
}

object IntegratedCircuitCardPublicKeyRemainder
  extends EMVDefaultBinaryWithVarLengthSpec[IntegratedCircuitCardPublicKeyRemainder] {

  override val maxLength = 252

  override val minLength = 1

  val tag  = berTag"9F48"


  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(IntegratedCircuitCardPublicKeyRemainder, parseB(_))

}