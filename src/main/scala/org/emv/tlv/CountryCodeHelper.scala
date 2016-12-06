package org.emv.tlv

import java.util.Currency

import com.neovisionaries.i18n.CountryCode
import org.tlv.HexUtils

/**
  * Created by lau on 11/7/16.
  */
trait CountryCodeHelper  {

  override def toString: String =
    s"""${super.toString}
       |\t${countryCode.getName}
     """.stripMargin

  val value: Seq[Byte] =  CountryCodeHelper.toValue(countryCode.getNumeric)

  val countryCode: CountryCode

}

object CountryCodeHelper {

  def toString(countryCode: CountryCode): String = s"${countryCode.getName} " +
    s"- ${countryCode.getAlpha3} (${HexUtils.toHex(toValue(countryCode.getNumeric))})"

  def toValue(num: Int) =  HexUtils.hex2Bytes(f"$num%04d")

  def getCountryCodeInstance(numericCode: Int): Option[CountryCode] =
    CountryCode.getByCode(numericCode) match {
      case null => None
      case v => Some(v)
    }

  def getCountryCodeInstance(numericCode: String): Option[CountryCode] =
    CountryCode.getByCode(numericCode) match {
      case null => None
      case v => Some(v)
    }

}
