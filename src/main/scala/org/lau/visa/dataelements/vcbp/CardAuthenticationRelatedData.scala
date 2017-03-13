package org.lau.visa.dataelements.vcbp


import fastparse.byte.all._
import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.{READRECORDResponseMessageTemplate, ResponseMessageTemplateFormat2}
import org.lau.tlv.ber._
import org.lau.visa.dataelements.CardTransactionQualifiers
import scodec.bits.ByteVector

case class CardAuthenticationRelatedData(data: (ByteVector, ByteVector, CardTransactionQualifiers))
  extends EMVTLVLeaf with TemplateTag {

  val fDDAVersionNumber = data._1

  val cardUnpredictableNumber = data._2

  val cardTransactionQualifiers = data._3

  override val tag = CardAuthenticationRelatedData.tag

  override val templates: Set[BerTag] = Set(READRECORDResponseMessageTemplate.tag)

  override val value = fDDAVersionNumber ++ cardUnpredictableNumber ++ cardTransactionQualifiers.value

}

object CardAuthenticationRelatedData extends EMVBinaryWithLengthSpec[(ByteVector, ByteVector, CardTransactionQualifiers), CardAuthenticationRelatedData] {

  val tag = berTag"9F69"

  def parser = parseEMVBySpec(CardAuthenticationRelatedData, parseCardAuthenticationRelatedData(_))

  def parseCardAuthenticationRelatedData(length: Int): Parser[(ByteVector, ByteVector, CardTransactionQualifiers)] = for(
    vn <- parseB(1);
    cun <- parseB(4);
    ctq <- parseB(2)
  ) yield((vn, cun, CardTransactionQualifiers(ctq)))

  override val length: Int = 7

}
