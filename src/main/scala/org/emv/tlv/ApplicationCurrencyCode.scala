package org.emv.tlv

import java.util.Currency
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._
import scodec.bits.ByteVector

/**
  * Created by lau on 6/2/16.
  */
case class ApplicationCurrencyCode(val currency: Currency)
  extends EMVTLVLeafWithCurrency with TemplateTag {

  override val tag = ApplicationCurrencyCode.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)
}

object ApplicationCurrencyCode extends EMVCurrencySpec[ApplicationCurrencyCode] {

  val tag = berTag"9F42"

  import fastparse.byte.all.Parser
  import org.emv.tlv.EMVTLV.EMVTLVParser._


  def parser = parseEMVBySpec(ApplicationCurrencyCode, parseCurrencyCode(_))

}
