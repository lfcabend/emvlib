package org.emv.tlv


import com.neovisionaries.i18n.CountryCode
import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

case class IssuerCountryCodeA3(val countryCode: CountryCode)
  extends EMVTLVLeafWithCountryCode with TemplateTag {

  override val tag = IssuerCountryCode.tag

  override val templates = Set(FileControlInformationIssuerDiscretionaryData.tag,
    DirectoryDiscretionaryTemplate.tag)
}

object IssuerCountryCodeA3 extends EMVCountryCodeSpecA3[IssuerCountryCodeA3] {

  val tag = berTag"5F56"

  def parser = parseEMVBySpec(IssuerCountryCodeA3, parseCountryCode(_))

}

