package org.emv.tlv

import java.util.Currency

import com.neovisionaries.i18n.CountryCode
import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/9/16.
  */
case class IssuerCountryCode(val countryCode: CountryCode)
  extends EMVTLVLeafWithCountryCode with TemplateTag {

  override val tag = IssuerCountryCode.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)

}

object IssuerCountryCode extends EMVCountryCodeSpec[IssuerCountryCode] {

  val tag = berTag"5F28"

  def parser = parseEMVBySpec(IssuerCountryCode, parseCountryCode(_))

}