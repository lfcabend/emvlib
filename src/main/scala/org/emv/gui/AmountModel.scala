package org.emv.gui

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


}
