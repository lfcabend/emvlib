package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._
/**
  * Created by lau on 6/6/16.
  */
case class ApplicationReferenceCurrencyExponent(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = ApplicationReferenceCurrencyExponent.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)
}


object ApplicationReferenceCurrencyExponent extends EMVDefaultBinaryWithVarLengthSpec[ApplicationReferenceCurrencyExponent] {

  val tag = berTag"9F43"

  override val maxLength = 4

  override val minLength = 1

  //todo parse four numbers
  def parser = parseEMVBySpec(ApplicationReferenceCurrencyExponent, parseB(_))


}
