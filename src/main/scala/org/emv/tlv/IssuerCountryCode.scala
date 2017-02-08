package org.emv.tlv

import java.util.Currency

import com.neovisionaries.i18n.CountryCode
import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVCountryCodeSpec, EMVTLVLeafWithCountryCode}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/9/16.
  */
case class IssuerCountryCode(val countryCode: CountryCode) extends EMVTLVLeafWithCountryCode {

  override val tag: BerTag = IssuerCountryCode.tag

}

object IssuerCountryCode extends EMVCountryCodeSpec[IssuerCountryCode] {

  val tag: BerTag = berTag"5F28"

  def parser: Parser[IssuerCountryCode] =
    parseEMVBySpec(IssuerCountryCode, parseCountryCode(_))

}