package org.lau.visa.dataelements

import org.emv.commands.GPOResponseFormat2
import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.{READRECORDResponseMessageTemplate, ResponseMessageTemplateFormat2}
import org.lau.tlv.ber._
import scodec.bits.ByteVector

case class FormFactorIndicator(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = FormFactorIndicator.tag

  override val templates: Set[BerTag] = Set(ResponseMessageTemplateFormat2.tag, READRECORDResponseMessageTemplate.tag)

}

object FormFactorIndicator extends EMVDefaultBinaryWithLengthSpec[FormFactorIndicator] {

  val tag = berTag"9F6E"

  def parser = parseEMVBySpec(FormFactorIndicator, parseB(_))

  override val length = 4
}
