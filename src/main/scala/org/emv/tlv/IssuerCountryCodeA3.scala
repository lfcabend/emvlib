package org.emv.tlv


import com.neovisionaries.i18n.CountryCode
import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVCountryCodeSpecA3, EMVTLVLeafWithCountryCode}
import org.lau.tlv.ber._
import scodec.bits._

case class IssuerCountryCodeA3(val countryCode: CountryCode) extends EMVTLVLeafWithCountryCode {


  override val tag: BerTag = IssuerCountryCode.tag

}

object IssuerCountryCodeA3 extends EMVCountryCodeSpecA3[IssuerCountryCodeA3] {

  val tag: BerTag = berTag"5F56"

  def parser: Parser[IssuerCountryCodeA3] =
    parseEMVBySpec(IssuerCountryCodeA3, parseCountryCode(_))


}

