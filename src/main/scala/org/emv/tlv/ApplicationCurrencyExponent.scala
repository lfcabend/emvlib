package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._


case class ApplicationCurrencyExponent(override val value: ByteVector)
  extends EMVTLVLeafNTextable with TemplateTag {

  override val tag = ApplicationCurrencyExponent.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)

}

object ApplicationCurrencyExponent extends EMVDefaultNumericWithLengthSpec[ApplicationCurrencyExponent] {

  val tag = berTag"9F44"

  import fastparse.byte.all.Parser
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(ApplicationCurrencyExponent,
    parseN(ApplicationCurrencyExponent)(_))

  override val max: Int = 1

  override val min: Int = 1

  override val length: Int = 1

}
