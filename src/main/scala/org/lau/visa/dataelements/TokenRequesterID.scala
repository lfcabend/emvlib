package org.lau.visa.dataelements

import org.emv.commands.GPOResponseFormat2
import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.{READRECORDResponseMessageTemplate, ResponseMessageTemplateFormat2}
import org.lau.tlv.ber._
import scodec.bits.ByteVector

case class TokenRequesterID(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = TokenRequesterID.tag

  override val templates: Set[BerTag] = Set(ResponseMessageTemplateFormat2.tag, READRECORDResponseMessageTemplate.tag)

}

object TokenRequesterID extends EMVDefaultBinaryWithLengthSpec[TokenRequesterID] {

  val tag = berTag"9F19"

  def parser = parseEMVBySpec(TokenRequesterID, parseB(_))

  override val length = 6
}
