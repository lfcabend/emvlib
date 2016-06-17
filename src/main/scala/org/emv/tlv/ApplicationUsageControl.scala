package org.emv.tlv

import org.emv.tlv.ByteUtils._
import org.emv.tlv.EMVTLV._
import org.tlv.TLV.BerTag

/**
  * Created by lau on 6/8/16.
  */
case class ApplicationUsageControl(override val value: Seq[Byte])
  extends EMVTLVLeaf {

  override val tag: BerTag = ApplicationUsageControl.tag

  override def toString: String = {
    s"""${super.toString}
       |\tByte 1 bit 8 valid for domestic cash             : ${isValidForDomesticCash}
       |\t       bit 7 valid for international cash        : ${isValidForInterInternationalCash}
       |\t       bit 6 valid for domestic goods            : ${isValidForDomesticGoods}
       |\t       bit 5 valid for international goods       : ${isValidForInternationalGoods}
       |\t       bit 4 valid for domestic services         : ${isValidForDomesticServices}
       |\t       bit 3 valid for domestic services         : ${isValidForDomesticServices}
       |\t       bit 2 valid at ATM's                      : ${isValidAtATM}
       |\t       bit 1 valid at terminals other then ATM's : ${isValidAtOtherTerminals}
       |\tByte 2 bit 8 Domestic cashback allowed           : ${isDomesticCashBackAllowed}
       |\t       bit 7 International cashback allowed      : ${isInternationalCashBackAllowed}
    """.stripMargin
  }

  def isValidForDomesticCash: Boolean = isBitSetInByte(value, 8, 1)

  def withValidForDomesticCashSet: ApplicationUsageControl =
    ApplicationUsageControl(withBitInByteSet(value, 8, 1))

  def withValidForDomesticCashUnSet: ApplicationUsageControl =
    ApplicationUsageControl(withBitInByteUnSet(value, 8, 1))

  def isValidForInterInternationalCash: Boolean = isBitSetInByte(value, 7, 1)

  def withValidForInternationalCashSet: ApplicationUsageControl =
    ApplicationUsageControl(withBitInByteSet(value, 7, 1))

  def withValidForInternationalCashUnSet: ApplicationUsageControl =
    ApplicationUsageControl(withBitInByteUnSet(value, 7, 1))

  def isValidForDomesticGoods: Boolean = isBitSetInByte(value, 6, 1)

  def withValidDomesticGoodsSet: ApplicationUsageControl =
    ApplicationUsageControl(withBitInByteSet(value, 6, 1))

  def withValidForDomesticGoodsUnSet: ApplicationUsageControl =
    ApplicationUsageControl(withBitInByteUnSet(value, 6, 1))

  def isValidForInternationalGoods: Boolean = isBitSetInByte(value, 5, 1)

  def withValidInternationalGoodsSet: ApplicationUsageControl =
    ApplicationUsageControl(withBitInByteSet(value, 5, 1))

  def withValidForInternationGoodsUnSet: ApplicationUsageControl =
    ApplicationUsageControl(withBitInByteUnSet(value, 5, 1))

  def isValidForDomesticServices: Boolean = isBitSetInByte(value, 4, 1)

  def withValidDomesticServicesSet: ApplicationUsageControl =
    ApplicationUsageControl(withBitInByteSet(value, 4, 1))

  def withValidForDomesticServicesUnSet: ApplicationUsageControl =
    ApplicationUsageControl(withBitInByteUnSet(value, 4, 1))

  def isValidForInternationalServices: Boolean = isBitSetInByte(value, 3, 1)

  def withValidInternationalServicesSet: ApplicationUsageControl =
    ApplicationUsageControl(withBitInByteSet(value, 3, 1))

  def withValidForInternationalServicesUnSet: ApplicationUsageControl =
    ApplicationUsageControl(withBitInByteUnSet(value, 3, 1))

  def isValidAtATM: Boolean = isBitSetInByte(value, 2, 1)

  def withValidAtATMSet: ApplicationUsageControl =
    ApplicationUsageControl(withBitInByteSet(value, 2, 1))

  def withValidAtATMUnSet: ApplicationUsageControl =
    ApplicationUsageControl(withBitInByteUnSet(value, 2, 1))

  def isValidAtOtherTerminals: Boolean = isBitSetInByte(value, 1, 1)

  def withValidAtOtherTerminalsSet: ApplicationUsageControl =
    ApplicationUsageControl(withBitInByteSet(value, 1, 1))

  def withValidAtOtherTerminalsUnSet: ApplicationUsageControl =
    ApplicationUsageControl(withBitInByteUnSet(value, 1, 1))

  def isDomesticCashBackAllowed: Boolean = isBitSetInByte(value, 8, 2)

  def withDomesticCashBackAllowedSet: ApplicationUsageControl =
    ApplicationUsageControl(withBitInByteSet(value, 8, 2))

  def withDomesticCashBackAllowedUnSet: ApplicationUsageControl =
    ApplicationUsageControl(withBitInByteUnSet(value, 8, 2))

  def isInternationalCashBackAllowed: Boolean = isBitSetInByte(value, 7, 2)

  def withInternationalCashBackAllowedSet: ApplicationUsageControl =
    ApplicationUsageControl(withBitInByteSet(value, 7, 2))

  def withInternationalCashBackAllowedUnSet: ApplicationUsageControl =
    ApplicationUsageControl(withBitInByteUnSet(value, 7, 2))

}

object ApplicationUsageControl extends EMVDefaultBinaryWithLengthSpec[ApplicationUsageControl] {

  val tag: BerTag = "9F07"

  val length: Int = 2

}