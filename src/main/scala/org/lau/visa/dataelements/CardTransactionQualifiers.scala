package org.lau.visa.dataelements


import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv._
import org.lau.tlv.ber._
import scodec.bits.ByteVector


case class CardTransactionQualifiers(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = CardTransactionQualifiers.tag

  override val templates: Set[BerTag] = Set(ResponseMessageTemplateFormat2.tag)

}

object CardTransactionQualifiers extends EMVDefaultBinaryWithLengthSpec[CardTransactionQualifiers] {

  val tag = berTag"9F6C"

  def parser = parseEMVBySpec(CardTransactionQualifiers, parseB(_))

  override val length = 2
}
