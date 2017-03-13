package org.emv.tlv

import java.util.Currency

import org.lau.tlv.ber._
import scodec.bits._

import scodec.bits.ByteVector

import scala.collection.JavaConverters._

/**
  * Created by lau on 6/4/16.
  */
trait CurrencyHelper {

  override def toString: String =
    s"""${super.toString}
       |\t${CurrencyHelper.toString(currency)}""".stripMargin

  val value: ByteVector =  CurrencyHelper.toValue(currency.getNumericCode)

  val currency: Currency

}

object CurrencyHelper {

  def toString(currency: Currency): String = s"${currency.getDisplayName} " +
    s"- ${currency.getCurrencyCode} (${toValue(currency.getNumericCode)})"

  def toValue(num: Int) =  ByteVector.fromValidHex(f"$num%04d")

  def getCurrencyInstance(numericCode: Int): Option[Currency] =
    Currency.getAvailableCurrencies().asScala.
      filter(x => x.getNumericCode == numericCode).headOption

}
