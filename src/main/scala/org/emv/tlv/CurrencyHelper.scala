package org.emv.tlv

import java.util.Currency
import org.tlv.HexUtils

import scala.collection.JavaConverters._

/**
  * Created by lau on 6/4/16.
  */
trait CurrencyHelper {

  override def toString: String =
    s"""${super.toString}
       |\t${CurrencyHelper.toString(currency)}
     """.stripMargin

  val value: Seq[Byte] =  CurrencyHelper.toValue(currency.getNumericCode)

  val currency: Currency

}

object CurrencyHelper {

  def toString(currency: Currency): String = s"${currency.getDisplayName} " +
    s"- ${currency.getCurrencyCode} (${HexUtils.toHex(toValue(currency.getNumericCode))})"


  def toValue(num: Int) =  HexUtils.hex2Bytes(f"$num%04d")

  def getCurrencyInstance(numericCode: Int): Option[Currency] =
    Currency.getAvailableCurrencies().asScala.
      filter(x => x.getNumericCode == numericCode).headOption

}
