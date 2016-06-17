package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.iso7816.AIDCategory._
import org.tlv.HexUtils
import org.tlv.TLV.{BerTLV, BerTag, BerTLVLeafT}

import org.tlv.HexUtils.hex2Bytes

trait AccountTypeT extends EMVTLVLeaf {

  override val tag: BerTag = AccountType.tag

  def isUnspecified: Boolean = accountType == AccountTypeEnum.UNSPECIFIED

  def isChequeDebit: Boolean = accountType == AccountTypeEnum.CHEQUE_DEBIT

  def isSavings: Boolean = accountType == AccountTypeEnum.SAVINGS

  def isCredit: Boolean = accountType == AccountTypeEnum.CREDIT

  def accountType = value(0) match {
    case x if (x == 0x00.toByte) => AccountTypeEnum.UNSPECIFIED
    case x if (x == 0x20.toByte) => AccountTypeEnum.CHEQUE_DEBIT
    case x if (x == 0x10.toByte) => AccountTypeEnum.SAVINGS
    case x if (x == 0x30.toByte) => AccountTypeEnum.CREDIT
    case _ => AccountTypeEnum.RFU
  }

  override val postFixLabel: Option[String] = accountType match {
    case AccountTypeEnum.RFU => None
    case _ => Some(accountType.toString)
  }

}

case class AccountType(override val value: Seq[Byte]) extends AccountTypeT

object AccountTypeEnum extends Enumeration {

  val UNSPECIFIED, CHEQUE_DEBIT, SAVINGS, CREDIT, RFU = Value

}

trait AccountTypeSpec extends EMVDefaultNumericWithLengthSpec[AccountType] {

  val length = 1

  val unSpecifiedValue = hex2Bytes("00")
  val chequeDebitValue = hex2Bytes("20")
  val savingsValue = hex2Bytes("10")
  val creditValue = hex2Bytes("30")

  val tag: BerTag = "5F57"

  override val max: Int = 2

  override val min: Int = 2
}

object AccountType extends AccountTypeSpec
