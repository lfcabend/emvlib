package org.emv.tlv

import org.emv.tlv.EMVTLV.{LeafToStringHelper, SingleTagParser}
import org.tlv.HexUtils
import org.tlv.TLV.{BerTLV, BerTag, BerTLVLeafT}

import org.tlv.HexUtils.hex2Bytes

/**
  * Created by lau on 5/29/16.
  */
case class AccountType(override val value: Seq[Byte])
  extends BerTLVLeafT with LeafToStringHelper {

  require(value.length == AccountType.length)

  override def tag(): BerTag = AccountType.tag

  def isUnspecified: Boolean = value == hex2Bytes("00").toList

  def isChequeDebit: Boolean = value == hex2Bytes("20").toList

  def isSavings: Boolean = value == hex2Bytes("10").toList

  def isCredit: Boolean = value == hex2Bytes("30").toList

  override def postFixLabel: Option[String] = this match {
    case UnSpecifiedAccountType() => Some("Unspecified")
    case ChequeDebitAccountType() => Some("ChequeDebit")
    case SavingsAccountType() => Some("Savings")
    case CreditAccountType() => Some("Credit")
    case _ => None
  }

}

object AccountType {

  val length = 1

  val unSpecifiedValue = hex2Bytes("00")
  val chequeDebitValue = hex2Bytes("20")
  val savingsValue = hex2Bytes("10")
  val creditValue = hex2Bytes("30")

  val tag: BerTag = "5F57"

  def unSpecified: AccountType = AccountType(unSpecifiedValue)

  def savings: AccountType = AccountType(savingsValue)

  def chequeDebit: AccountType = AccountType(chequeDebitValue)

  def credit: AccountType = AccountType(creditValue)

}

object UnSpecifiedAccountType {

  def unapply(at: AccountType): Boolean = at.isUnspecified

}

object ChequeDebitAccountType {

  def unapply(at: AccountType): Boolean = at.isChequeDebit

}

object SavingsAccountType {

  def unapply(at: AccountType): Boolean = at.isSavings

}

object CreditAccountType {

  def unapply(at: AccountType): Boolean = at.isCredit

}
