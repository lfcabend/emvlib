package org.emv.tlv

import fastparse.byte.all.Parser
import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._
import scodec.bits.ByteVector


case class SignedStaticApplicationData(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = SignedStaticApplicationData.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)

}

object SignedStaticApplicationData extends EMVDefaultBinaryWithVarLengthSpec[SignedStaticApplicationData] {

  val tag = berTag"93"

  override val maxLength = 255

  override val minLength = 0

  def parser = parseEMVBySpec(SignedStaticApplicationData, parseB(_))

}