package org.emv.tlv

import java.util.Currency

import com.neovisionaries.i18n.CountryCode
import org.emv.tlv.EMVTLV.{EMVCountryCodeSpec, EMVTLVLeafWithCountryCode}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/9/16.
  */
case class IssuerCountryCode(val countryCode: CountryCode) extends EMVTLVLeafWithCountryCode {

  override val tag: BerTag = IssuerCountryCode.tag

}

object IssuerCountryCode extends EMVCountryCodeSpec[IssuerCountryCode] {

  val tag: BerTag = "5F28"

}