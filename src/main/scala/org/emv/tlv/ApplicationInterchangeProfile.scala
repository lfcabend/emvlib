package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.ByteUtils._
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._
import org.emv.tlv.ByteUtils._
import org.emv.tlv.EMVTLV.EMVTLVParser._


/**
  * Created by lau on 6/5/16.
  */
case class ApplicationInterchangeProfile(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag: BerTag = ApplicationInterchangeProfile.tag

  override def toString: String = {
    s"""${super.toString}
       |\tByte 1 bit 7 SDA supported                     : ${isSDASupported}
       |\t       bit 6 DDA supported                     : ${isDDASupported}
       |\t       bit 5 cardholder verification supported : ${isCardholderVerificationSupported}
       |\t       bit 4 perform terminal risk management  : ${isPerformTerminalRiskManagement}
       |\t       bit 3 issuer authentication             : ${isIssuerAuthenitcationSupported}
       |\t       bit 1 CDA supported                     : ${isCDASupported}
       |\tByte 2 bit 8 contacless                        : ${isContactless}""".stripMargin
  }

  def isSDASupported: Boolean = isBitSetInByte(value, 7, 1)

  def withSDASupportedSet: ApplicationInterchangeProfile =
    ApplicationInterchangeProfile(withBitInByteSet(value, 7, 1))

  def withSDASupportedUnSet: ApplicationInterchangeProfile =
    ApplicationInterchangeProfile(withBitInByteUnSet(value, 7, 1))

  def isDDASupported: Boolean = isBitSetInByte(value, 6, 1)

  def withDDASupportedSet: ApplicationInterchangeProfile =
    ApplicationInterchangeProfile(withBitInByteSet(value, 6, 1))

  def withDDASupportedUnSet: ApplicationInterchangeProfile =
    ApplicationInterchangeProfile(withBitInByteUnSet(value, 6, 1))

  def isCardholderVerificationSupported: Boolean = isBitSetInByte(value, 5, 1)

  def withCardholderVerificationSupportedSet: ApplicationInterchangeProfile =
    ApplicationInterchangeProfile(withBitInByteSet(value, 5, 1))

  def withCardholderVerificationSupportedUnSet: ApplicationInterchangeProfile =
    ApplicationInterchangeProfile(withBitInByteUnSet(value, 5, 1))

  def isPerformTerminalRiskManagement: Boolean = isBitSetInByte(value, 4, 1)

  def withPerformTerminalRiskManagementSet: ApplicationInterchangeProfile =
    ApplicationInterchangeProfile(withBitInByteSet(value, 4, 1))

  def withPerformTerminalRiskManagementUnSet: ApplicationInterchangeProfile =
    ApplicationInterchangeProfile(withBitInByteUnSet(value, 4, 1))

  def isIssuerAuthenitcationSupported: Boolean = isBitSetInByte(value, 3, 1)

  def withIssuerAuthenitcationSupportedSet: ApplicationInterchangeProfile =
    ApplicationInterchangeProfile(withBitInByteSet(value, 3, 1))

  def withIssuerAuthenitcationSupportedUnSet: ApplicationInterchangeProfile =
    ApplicationInterchangeProfile(withBitInByteUnSet(value, 3, 1))

  def isCDASupported: Boolean = isBitSetInByte(value, 1, 1)

  def withCDASupportedSet: ApplicationInterchangeProfile =
    ApplicationInterchangeProfile(withBitInByteSet(value, 1, 1))

  def withCDASupportedUnSet: ApplicationInterchangeProfile =
    ApplicationInterchangeProfile(withBitInByteUnSet(value, 1, 1))

  def isContactless: Boolean = isBitSetInByte(value, 8, 2)

  def withContactlessSet: ApplicationInterchangeProfile =
    ApplicationInterchangeProfile(withBitInByteSet(value, 8, 2))

  def withContactlessUnSet: ApplicationInterchangeProfile =
    ApplicationInterchangeProfile(withBitInByteUnSet(value, 8, 2))

  override val templates = Set(ResponseMessageTemplateFormat2.tag)

}

object ApplicationInterchangeProfile extends EMVDefaultBinaryWithLengthSpec[ApplicationInterchangeProfile] {

  val tag: BerTag = berTag"82"

  val length: Int = 2

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[ApplicationInterchangeProfile] =
    parseEMVBySpec(ApplicationInterchangeProfile, parseB(_))

}
