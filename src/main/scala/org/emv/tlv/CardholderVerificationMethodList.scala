package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/23/16.
  */
case class CardholderVerificationMethodList(amountX: ByteVector,
                                            amountY: ByteVector,
                                            rules: List[CVMRule])
  extends EMVTLVLeaf with TemplateTag {

  override val value = amountX ++ amountY ++
    rules.foldRight[ByteVector](ByteVector.empty)((x, y) => x.value ++ y)

  override val tag = CardholderVerificationMethodList.tag

  override def toString =
    s"""${super.toString}
       |\tAmount X: ${amountX.toHex}
       |\tAmount Y: ${amountY.toHex}
       |${rules.map(_.toString).mkString("\n")}
     """.stripMargin

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)
}

case class CVMRule(applySucceedingRule: Boolean, methodCode: CVMMethodT, condition: CVMConditionT) {

  def appleSucceedingByteValue = if (applySucceedingRule) 0x40.toByte else 0x00.toByte

  def value: ByteVector = ByteVector((methodCode.value | appleSucceedingByteValue).toByte :: condition.value :: Nil)

  override def toString: String =
    s"""\tRule: ${value.toHex}
        | ${
      if (applySucceedingRule) "\t\tApply succeeding CV Rule if this CVM is unsuccessful"
      else "\t\tFail cardholder verification if this CVM is unsuccessful"
    }
        | \t\tMethod   : ${methodCode.toString}
        | \t\tCondition: ${condition.toString}
     """.stripMargin

}

trait CVMMethodT {

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

  override def toString: String = s"RFU: ${ByteVector(value).toHex}"

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

  override def toString: String = s"RFU: ${ByteVector(value).toHex}"

}

object CardholderVerificationMethodList extends EMVBinaryWithVarLengthSpec[(ByteVector, ByteVector, List[CVMRule]),
  CardholderVerificationMethodList] {

  override val tag = berTag"8E"

  override val maxLength = 252

  override val minLength = 10

  override def apply(v: (ByteVector, ByteVector, List[CVMRule]))=
    new CardholderVerificationMethodList(v._1, v._2, v._3)

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._
  import org.lau.tlv.ber.BerTLVParser._

  def parser = parseEMVBySpec(CardholderVerificationMethodList, parseCardholderVerificationMethodListValue(_))

  def parseCardholderVerificationMethodListValue(length: Int) = for {
    x <- AnyByte.!.rep(exactly = 4).!
    y <- AnyByte.!.rep(exactly = 4).!
    rules <- repParsingForXByte(parseCVMRule, length - 8)
  } yield (x, y, rules)

  def parseCVMRule = for {
    m <- parseCVMMethodAndFail
    c <- parseCVMCondition
  } yield (CVMRule(m._1, m._2, c))

  def parseCVMMethodAndFail: Parser[(Boolean, CVMMethodT)] = AnyByte.!.map (x => {
    val applySucceedingRule = (x.toByte() & 0x40.toByte) == 0x40.toByte
    (x.toByte()  & 0xBF) match {
      case FailCVM.value => (applySucceedingRule, FailCVM)
      case PlainTextPinICC.value => (applySucceedingRule, PlainTextPinICC)
      case EncipheredPINOnline.value => (applySucceedingRule, EncipheredPINOnline)
      case PlainTextPinICCAndSignature.value => (applySucceedingRule, PlainTextPinICCAndSignature)
      case EncipheredPINICC.value => (applySucceedingRule, EncipheredPINICC)
      case EncipheredPINICCAndSignature.value => (applySucceedingRule, EncipheredPINICCAndSignature)
      case Signature.value => (applySucceedingRule, Signature)
      case NoCVMRequired.value => (applySucceedingRule, NoCVMRequired)
      case default => (applySucceedingRule, RFUMethod(x.toByte()))
    }
  })

  def parseCVMCondition: Parser[CVMConditionT] = P(AnyElem.!.map(x => x.toByte() match {
    case Always.value => Always
    case UnattendedCash.value => UnattendedCash
    case NotUnattendedManualAndNotPurchaseWithCashback.value => NotUnattendedManualAndNotPurchaseWithCashback
    case TerminalSupportsCVM.value => TerminalSupportsCVM
    case ManualCash.value => ManualCash
    case PurchaseWithCashback.value => PurchaseWithCashback
    case ApplicationCurrencyAndUnderX.value => ApplicationCurrencyAndUnderX
    case ApplicationCurrencyAndOverX.value => ApplicationCurrencyAndOverX
    case ApplicationCurrencyAndUnderY.value => ApplicationCurrencyAndUnderY
    case ApplicationCurrencyAndOverY.value => ApplicationCurrencyAndOverY
    case x@default => RFUCondition(x)
  }))

}
