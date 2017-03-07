package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf, EMVTLVParser}
import org.emv.tlv.ByteUtils._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 5/31/16.
  */
trait AdditionalTerminalCapabilitiesT extends EMVTLVLeaf {

  override val tag = AdditionalTerminalCapabilities.tag

  override def toString = {
    s"""${super.toString}
       |\tByte 1 bit 8 cash               : $isCash
       |\t       bit 7 goods              : $isGoods
       |\t       bit 6 services           : $isServices
       |\t       bit 5 services           : $isCashback
       |\t       bit 4 services           : $isInquiry
       |\t       bit 3 services           : $isTransfer
       |\t       bit 2 services           : $isPayment
       |\t       bit 1 services           : $isAdministrative
       |\tByte 2 bit 8 Cash Deposit       : $isCashDeposit
       |\tByte 3 bit 8 Numeric Keys       : $isNumericKeys
       |\t       bit 7 Special Keys       : $isSpecialKeys
       |\t       bit 6 Command Keys       : $isCommandKeys
       |\t       bit 5 function Keys      : $isFunctionKeys
       |\tByte 4 bit 8 Print, attendent   : $isFunctionKeys
       |\t       bit 7 Print, cardholder  : $isFunctionKeys
       |\t       bit 6 Display, attendent : $isFunctionKeys
       |\t       bit 5 Display, cardholder: $isFunctionKeys
       |\t       bit 2 Code table 10      : $isCodeTable10
       |\t       bit 1 Code table 9       : $isCodeTable9
       |\tByte 5 bit 8 Code table 8       : $isCodeTable8
       |\t       bit 7 Code table 7       : $isCodeTable7
       |\t       bit 6 Code table 6       : $isCodeTable6
       |\t       bit 5 Code table 5       : $isCodeTable5
       |\t       bit 4 Code table 4       : $isCodeTable4
       |\t       bit 3 Code table 3       : $isCodeTable3
       |\t       bit 2 Code table 2       : $isCodeTable2
       |\t       bit 1 Code table 1       : $isCodeTable1
    """.stripMargin
  }

  def isCash: Boolean = isBitSetInByte(value, 8, 1)

  def withCashSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 8, 1))

  def withCashUnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 8, 1))

  def isGoods: Boolean = isBitSetInByte(value, 7, 1)

  def withGoodsSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 7, 1))

  def withGoodsUnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 7, 1))

  def isServices: Boolean = isBitSetInByte(value, 6, 1)

  def withServicesSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 6, 1))

  def withServicesUnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 6, 1))

  def isCashback: Boolean = isBitSetInByte(value, 5, 1)

  def withCashbackSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 5, 1))

  def withCashbackUnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 5, 1))

  def isInquiry: Boolean = isBitSetInByte(value, 4, 1)

  def withInquirySet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 4, 1))

  def withInquiryUnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 4, 1))

  def isTransfer: Boolean = isBitSetInByte(value, 3, 1)

  def withTransferSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 3, 1))

  def withTransferUnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 3, 1))

  def isPayment: Boolean = isBitSetInByte(value, 2, 1)

  def withPaymentSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 2, 1))

  def withPaymentUnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 2, 1))

  def isAdministrative: Boolean = isBitSetInByte(value, 1, 1)

  def withAdministrativeSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 1, 1))

  def withAdministrativeUnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 1, 1))

  def isCashDeposit: Boolean = isBitSetInByte(value, 8, 2)

  def withCashDepositSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 8, 2))

  def withCashDepositUnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 8, 2))

  def isNumericKeys: Boolean = isBitSetInByte(value, 8, 3)

  def withNumericKeysSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 8, 3))

  def withNumericKeysUnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 8, 3))

  def isSpecialKeys: Boolean = isBitSetInByte(value, 7, 3)

  def withSpecialSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 7, 3))

  def withSpecialUnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 7, 3))

  def isCommandKeys: Boolean = isBitSetInByte(value, 6, 3)

  def withCommandSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 6, 3))

  def withCommandUnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 6, 3))

  def isFunctionKeys: Boolean = isBitSetInByte(value, 5, 3)

  def withFunctionKeysSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 5, 3))

  def withFunctionKeysUnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 5, 3))

  def isPrintAttendant: Boolean = isBitSetInByte(value, 8, 4)

  def withPrintAttendantSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 8, 4))

  def withPrintAttendantUnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 8, 4))

  def isPrintCardholder: Boolean = isBitSetInByte(value, 7, 4)

  def withPrintCardholderSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 7, 4))

  def withPrintCardholderUnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 7, 4))

  def isDisplayAttendant: Boolean = isBitSetInByte(value, 6, 4)

  def withDisplayAttendantSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 6, 4))

  def withDisplayAttendantUnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 6, 4))

  def isDisplayCardholder: Boolean = isBitSetInByte(value, 5, 4)

  def withDisplayCardholderSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 5, 4))

  def withDisplayCardholderUnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 5, 4))

  def isCodeTable10: Boolean = isBitSetInByte(value, 2, 4)

  def withCodeTable10Set: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 2, 4))

  def withCodeTable10UnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 2, 4))


  def isCodeTable9: Boolean = isBitSetInByte(value, 1, 4)

  def withCodeTable9Set: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 1, 4))

  def withCodeTable9UnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 1, 4))


  def isCodeTable8: Boolean = isBitSetInByte(value, 8, 5)

  def withCodeTable8Set: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 8, 5))

  def withCodeTable8UnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 8, 5))


  def isCodeTable7: Boolean = isBitSetInByte(value, 7, 5)

  def withCodeTable7Set: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 7, 5))

  def withCodeTable7UnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 7, 5))


  def isCodeTable6: Boolean = isBitSetInByte(value, 6, 5)

  def withCodeTable6Set: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 6, 5))

  def withCodeTable6UnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 6, 5))


  def isCodeTable5: Boolean = isBitSetInByte(value, 5, 5)

  def withCodeTable5Set: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 5, 5))

  def withCodeTable5UnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 5, 5))


  def isCodeTable4: Boolean = isBitSetInByte(value, 4, 5)

  def withCodeTable4Set: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 4, 5))

  def withCodeTable4UnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 4, 5))


  def isCodeTable3: Boolean = isBitSetInByte(value, 3, 5)

  def withCodeTable3Set: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 3, 5))

  def withCodeTable3UnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 3, 5))


  def isCodeTable2: Boolean = isBitSetInByte(value, 2, 5)

  def withCodeTable2Set: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 2, 5))

  def withCodeTable2UnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 2, 5))


  def isCodeTable1: Boolean = isBitSetInByte(value, 1, 5)

  def withCodeTable1Set: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteSet(value, 1, 5))

  def withCodeTable1UnSet: AdditionalTerminalCapabilities =
    AdditionalTerminalCapabilities(withBitInByteUnSet(value, 1, 5))

}

case class AdditionalTerminalCapabilities(override val value: ByteVector) extends AdditionalTerminalCapabilitiesT {

  require(value.length == AdditionalTerminalCapabilities.length)
}


trait AdditionalTerminalCapabilitiesSpec extends EMVDefaultBinaryWithLengthSpec[AdditionalTerminalCapabilities] {

  val tag = berTag"9F40"

  val length = 5

  def apply() = new AdditionalTerminalCapabilities(hex"0000000000")

}

object AdditionalTerminalCapabilities extends AdditionalTerminalCapabilitiesSpec {

  import fastparse.byte.all.Parser
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(AdditionalTerminalCapabilities, parseB(_))

}
