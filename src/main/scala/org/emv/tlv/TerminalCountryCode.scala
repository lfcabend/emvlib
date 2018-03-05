package org.emv.tlv

import com.neovisionaries.i18n.CountryCode
import fastparse.byte.all._
import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._
import scodec.bits.ByteVector

case class TerminalCountryCode(val countryCode: CountryCode) extends EMVTLVLeafWithCountryCode {

  override val tag = TerminalCountryCode.tag

}

object TerminalCountryCode extends EMVCountryCodeSpec[TerminalCountryCode] {

  val tag = berTag"9F1A"

  def parser = parseEMVBySpec(TerminalCountryCode, parseCountryCode(_))

}