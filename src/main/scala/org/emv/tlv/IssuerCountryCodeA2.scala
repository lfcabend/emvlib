package org.emv.tlv

import com.neovisionaries.i18n.CountryCode
import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVCountryCodeSpecA2, EMVTLVLeafWithCountryCode}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/9/16.
  */
case class IssuerCountryCodeA2 (val countryCode: CountryCode) extends EMVTLVLeafWithCountryCode {

  override val tag: BerTag = IssuerCountryCode.tag

}

object IssuerCountryCodeA2 extends EMVCountryCodeSpecA2[IssuerCountryCodeA2] {

  val tag: BerTag = berTag"5F55"

  def parser: Parser[IssuerCountryCodeA2] =
    parseEMVBySpec(IssuerCountryCodeA2, parseCountryCode(_))

}
