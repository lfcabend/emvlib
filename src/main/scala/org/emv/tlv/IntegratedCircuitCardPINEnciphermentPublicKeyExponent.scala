package org.emv.tlv

import org.emv.tlv.EMVTLV._
import scodec.bits._
import org.lau.tlv.ber._

/**
  * Created by lau on 11/7/16.
  */

case class IntegratedCircuitCardPINEnciphermentPublicKeyExponent(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = IntegratedCircuitCardPINEnciphermentPublicKeyExponent.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)
}

object IntegratedCircuitCardPINEnciphermentPublicKeyExponent
  extends EMVDefaultBinaryWithVarLengthSpec[IntegratedCircuitCardPINEnciphermentPublicKeyExponent] {

  override val maxLength = 3

  override val minLength = 1

  val tag  = berTag"9F2E"


  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(IntegratedCircuitCardPINEnciphermentPublicKeyExponent, parseB(_))

}
