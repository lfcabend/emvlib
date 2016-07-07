package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.tlv.HexUtils
import org.tlv.TLV.BerTag
import org.tlv.HexUtils._

/**
  * Created by lau on 6/23/16.
  */
case class CardholderVerificationMethodList(amountX: Seq[Byte],
                                            amountY: Seq[Byte],
                                            rules: List[CVMRule]) extends EMVTLVLeaf {

  override val value: Seq[Byte] = amountX ++ amountY ++ rules.map(_.value).flatten

  override val tag: BerTag = CardholderVerificationMethodList.tag

  override def toString: String =
    s"""${super.toString}
       |\tAmount X: ${amountX.toHex}
       |\tAmount Y: ${amountY.toHex}
       |${rules.map(_.toString).mkString("\n")}
     """.stripMargin

}

case class CVMRule(applySucceedingRule: Boolean, methodCode: CVMMethodT, condition: CVMConditionT) {

  def appleSucceedingByteValue =  if(applySucceedingRule) 0x40.toByte else 0x00.toByte

  def value: Seq[Byte] = (methodCode.value | appleSucceedingByteValue).toByte :: condition.value :: Nil

  override def toString: String =
    s"""\tRule: ${value.toHex}
       | ${if (applySucceedingRule) "\t\tApply succeeding CV Rule if this CVM is unsuccessful"
           else "\t\tFail cardholder verification if this CVM is unsuccessful"}
       | \t\tMethod   : ${methodCode.toString}
       | \t\tCondition: ${condition.toString}
     """.stripMargin

}

trait CVMMethodT  {

  val value: Byte

}

object FailCVM extends CVMMethodT {

  val value = 0x00.toByte

  override def toString: String = "Fail CVM processing"

}

object PlainTextPinICC extends CVMMethodT {

  val value = 0x01.toByte

  override def toString: String = "Plaintext PIN verification performed by ICC"

}

object EncipheredPINOnline extends CVMMethodT {

  val value = 0x02.toByte

  override def toString: String = "Enciphered PIN verified online"

}

object PlainTextPinICCAndSignature extends CVMMethodT {

  val value = 0x03.toByte

  override def toString: String = "Plaintext PIN verification performed by ICC and signature (paper)"

}

object EncipheredPINICC extends CVMMethodT {

  val value = 0x04.toByte

  override def toString: String = "Enciphered PIN verification performed by ICC"

}

object EncipheredPINICCAndSignature extends CVMMethodT {

  val value = 0x05.toByte

  override def toString: String = "Enciphered PIN verification performed by ICC and signature (paper)"

}

object Signature extends CVMMethodT {

  val value = 0x1E.toByte

  override def toString: String = "Signature (paper)"

}

object NoCVMRequired extends CVMMethodT {

  val value = 0x1F.toByte

  override def toString: String = "No CVM required"

}

case class RFUMethod(value: Byte) extends CVMMethodT {

  override def toString: String = s"RFU: ${HexUtils.toHex(List(value))}"

}


trait CVMConditionT {

  val value: Byte

}

object Always extends CVMConditionT {

  val value = 0x00.toByte

  override def toString: String = "Always"

}

object UnattendedCash extends CVMConditionT {

  val value = 0x01.toByte

  override def toString: String = "If unattended cash"

}

object NotUnattendedManualAndNotPurchaseWithCashback extends CVMConditionT {

  val value = 0x02.toByte

  override def toString: String = "If not unattended cash and not manual cash and not purchase with cashback"

}

object TerminalSupportsCVM extends CVMConditionT {

  val value = 0x03.toByte

  override def toString: String = "If terminal supports the CVM"

}

object ManualCash extends CVMConditionT {

  val value = 0x04.toByte

  override def toString: String = "If manual cash"

}

object PurchaseWithCashback extends CVMConditionT {

  val value = 0x05.toByte

  override def toString: String = "If purchase with cashback"

}

object ApplicationCurrencyAndUnderX extends CVMConditionT {

  val value = 0x06.toByte

}

object ApplicationCurrencyAndOverX extends CVMConditionT {

  val value = 0x07.toByte

  override def toString: String = "If transaction is in the application currency and is under X value"

}

object ApplicationCurrencyAndUnderY extends CVMConditionT {

  val value = 0x08.toByte

  override def toString: String = "If transaction is in the application currency and is over X value"

}

object ApplicationCurrencyAndOverY extends CVMConditionT {

  val value = 0x09.toByte

  override def toString: String = "If transaction is in the application currency and is under Y value"

}

case class RFUCondition(value: Byte) extends CVMConditionT {

  override def toString: String = s"RFU: ${HexUtils.toHex(List(value))}"

}

object CardholderVerificationMethodList extends EMVBinaryWithVarLengthSpec[(Seq[Byte], Seq[Byte], List[CVMRule]),
  CardholderVerificationMethodList] {

  override val tag: BerTag = "8E"

  override val maxLength: Int = 252

  override val minLength: Int = 10

  override def apply(v: (Seq[Byte], Seq[Byte], List[CVMRule])): CardholderVerificationMethodList =
    new CardholderVerificationMethodList(v._1, v._2, v._3)

}
