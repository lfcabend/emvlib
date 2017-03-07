package org.emv.tlv

import org.emv.tlv.EMVTLV._
import scodec.bits._
import org.lau.tlv.ber._

/**
  * Created by lau on 11/7/16.
  */
case class IntegratedCircuitCardPublicKeyCertificate(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = IntegratedCircuitCardPublicKeyCertificate.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)
}

object IntegratedCircuitCardPublicKeyCertificate
  extends EMVDefaultBinaryWithVarLengthSpec[IntegratedCircuitCardPublicKeyCertificate] {

  override val maxLength = 252

  override val minLength = 1

  val tag  = berTag"9F46"


  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(IntegratedCircuitCardPublicKeyCertificate, parseB(_))

}