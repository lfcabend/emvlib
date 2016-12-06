package org.emv.tlv

import com.neovisionaries.i18n.CountryCode
import org.emv.tlv.EMVTLV.{EMVCountryCodeSpecA2, EMVTLVLeafWithCountryCode}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/9/16.
  */
case class IssuerCountryCodeA2 (val countryCode: CountryCode) extends EMVTLVLeafWithCountryCode {

  override val tag: BerTag = IssuerCountryCode.tag

}

object IssuerCountryCodeA2 extends EMVCountryCodeSpecA2[IssuerCountryCodeA2] {

  val tag: BerTag = "5F55"

}
