package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/7/16.
  */
case class IntegratedCircuitCardPINEnciphermentPublicKeyCertificate(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = IntegratedCircuitCardPINEnciphermentPublicKeyCertificate.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)
}

object IntegratedCircuitCardPINEnciphermentPublicKeyCertificate
  extends EMVDefaultBinaryWithVarLengthSpec[IntegratedCircuitCardPINEnciphermentPublicKeyCertificate] {

  override val maxLength = 252

  override val minLength = 0

  val tag  = berTag"9F2D"


  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(IntegratedCircuitCardPINEnciphermentPublicKeyCertificate, parseB(_))

}
