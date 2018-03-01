package org.emv.gui

import org.emv.tlv.AmountAuthorized
import scodec.bits.ByteVector._
import scodec.bits._

/**
  * Created by Lau on 5/6/2017.
  */
class AmountModel {

  val DOT: String = "."

  var amount: String = ""

  def addInput(number: String) = {
    amount = amount + number
  }

  def removeInput() = amount =
    if (amount.length >= 1) amount.substring(0, amount.length - 1)
    else amount

  def getAmount() = amount.length match {
    case k if k == 0 => "0.00"
    case k if k == 1 => "0.0" + amount
    case k if k == 2 => "0." + amount
    case k if k > 2 => amount.substring(0, amount.length - 2) + "." +
      amount.substring(amount.length - 2, amount.length)
  }

  def reset(): Unit = amount = ""

  def getAmountAuthorized() = {
    val amountWithNoDot = getAmount().replaceAll("[^0-9]", "")
    val v = String.format("%012d", amountWithNoDot.toInt.asInstanceOf[AnyRef])
    AmountAuthorized(fromValidHex(v))
  }

}
