package org.emv.tlv

import com.neovisionaries.i18n.CountryCode
import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/9/16.
  */
case class IssuerCountryCodeA2 (val countryCode: CountryCode)
  extends EMVTLVLeafWithCountryCode with TemplateTag {

  override val tag = IssuerCountryCode.tag

  override val templates = Set(FileControlInformationIssuerDiscretionaryData.tag,
    DirectoryDiscretionaryTemplate.tag)

}

object IssuerCountryCodeA2 extends EMVCountryCodeSpecA2[IssuerCountryCodeA2] {

  val tag  = berTag"5F55"

  def parser = parseEMVBySpec(IssuerCountryCodeA2, parseCountryCode(_))

}
