package org.emv.tlv


import com.neovisionaries.i18n.CountryCode
import org.emv.tlv.EMVTLV.{EMVCountryCodeSpecA3, EMVTLVLeafWithCountryCode}
import org.tlv.TLV.BerTag

case class IssuerCountryCodeA3(val countryCode: CountryCode) extends EMVTLVLeafWithCountryCode {


  override val tag: BerTag = IssuerCountryCode.tag

}

object IssuerCountryCodeA3 extends EMVCountryCodeSpecA3[IssuerCountryCodeA3] {

  val tag: BerTag = "5F56"

}

