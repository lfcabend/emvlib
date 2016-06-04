package org.emv.tlv

import java.util.Currency
import scala.collection.JavaConverters._

/**
  * Created by lau on 6/4/16.
  */
trait CurrencyHelper {

  def value: Seq[Byte]

  def currency: Option[Currency] = getCurrencyInstance(BigInt(1, value.toArray).intValue())

  def getCurrencyInstance(numericCode: Int): Option[Currency] =
    Currency.getAvailableCurrencies().asScala.
      filter(x => x.getNumericCode == numericCode).headOption

}
