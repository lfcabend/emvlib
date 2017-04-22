package org.lau.visa.dataelements

import org.emv.tlv.ByteUtils.{isBitSetInByte, withBitInByteSet, withBitInByteUnSet}
import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv._
import org.lau.tlv.ber._
import scodec.bits._

case class TerminalTransactionQualifiers(override val value: ByteVector)
  extends EMVTLVLeaf  {

  override val tag = TerminalTransactionQualifiers.tag

  override def toString: String = {
    s"""${super.toString}
       |\tByte 1 bit 8 MSD supported                         : ${isMSDSupported}
       |\t       bit 6 qVSDC supported                       : ${isQVSDCSupported}
       |\t       bit 5 EMV contact chip supported            : ${isEMVContactChipSupported}
       |\t       bit 4 Offline-only reader                   : ${isOfflineOnlyReader}
       |\t       bit 3 Online PIN supported                  : ${isOnlinePINSupported}
       |\t       bit 2 Signature supported                   : ${isSignatureSupported}
       |\t       bit 1 Offline Data Authentication supported : ${isOfflineDataAuthenticationSupported}
       |\tByte 2 bit 8 Online cryptogram required            : ${isOnlineCryptogramRequired}
       |\t       bit 7 CVM required                          : ${isCVMRequired}
       |\tByte 3 bit 8 Issuer Update Processing supported    : ${isIssuerUpdateProcessingSupported}
       |\t       bit 7 Mobile functionalityly supprted       : ${isMobileFunctionalitySupported}
       |""".stripMargin
  }

  def isMSDSupported: Boolean = isBitSetInByte(value, 8, 1)

  def withMSDSupportedSet: TerminalTransactionQualifiers =
    TerminalTransactionQualifiers(withBitInByteSet(value, 8, 1))

  def withMSDSupportedUnSet: ApplicationInterchangeProfile =
    ApplicationInterchangeProfile(withBitInByteUnSet(value, 8, 1))

  def isQVSDCSupported: Boolean = isBitSetInByte(value, 6, 1)

  def withQVSDCSupportedSet: TerminalTransactionQualifiers =
    TerminalTransactionQualifiers(withBitInByteSet(value, 6, 1))

  def withQVSDCSupportedUnSet: ApplicationInterchangeProfile =
    ApplicationInterchangeProfile(withBitInByteUnSet(value, 6, 1))

  def isEMVContactChipSupported: Boolean = isBitSetInByte(value, 5, 1)

  def withEMVContactChipSupportedSet: TerminalTransactionQualifiers =
    TerminalTransactionQualifiers(withBitInByteSet(value, 5, 1))

  def withEMVContactChipSupportedUnSet: ApplicationInterchangeProfile =
    ApplicationInterchangeProfile(withBitInByteUnSet(value, 5, 1))

  def isOfflineOnlyReader: Boolean = isBitSetInByte(value, 4, 1)

  def withOfflineOnlyReaderSet: TerminalTransactionQualifiers =
    TerminalTransactionQualifiers(withBitInByteSet(value, 4, 1))

  def withOfflineOnlyReaderUnSet: ApplicationInterchangeProfile =
    ApplicationInterchangeProfile(withBitInByteUnSet(value, 4, 1))


  def isOnlinePINSupported: Boolean = isBitSetInByte(value, 3, 1)

  def withOnlinePINSupportedSet: TerminalTransactionQualifiers =
    TerminalTransactionQualifiers(withBitInByteSet(value, 3, 1))

  def withOnlinePINSupportedUnSet: ApplicationInterchangeProfile =
    ApplicationInterchangeProfile(withBitInByteUnSet(value, 3, 1))

  def isSignatureSupported: Boolean = isBitSetInByte(value, 2, 1)

  def withSignatureSupportedSet: TerminalTransactionQualifiers =
    TerminalTransactionQualifiers(withBitInByteSet(value, 2, 1))

  def withSignatureSupportedUnSet: ApplicationInterchangeProfile =
    ApplicationInterchangeProfile(withBitInByteUnSet(value, 2, 1))

  def isOfflineDataAuthenticationSupported: Boolean = isBitSetInByte(value, 1, 1)

  def withOfflineDataAuthenticationSupportedSet: TerminalTransactionQualifiers =
    TerminalTransactionQualifiers(withBitInByteSet(value, 1, 1))

  def withOfflineDataAuthenticationSupportedUnSet: ApplicationInterchangeProfile =
    ApplicationInterchangeProfile(withBitInByteUnSet(value, 1, 1))

  def isOnlineCryptogramRequired: Boolean = isBitSetInByte(value, 8, 2)

  def withOnlineCryptogramRequiredSet: TerminalTransactionQualifiers =
    TerminalTransactionQualifiers(withBitInByteSet(value, 8, 2))

  def withOnlineCryptogramRequiredUnSet: ApplicationInterchangeProfile =
    ApplicationInterchangeProfile(withBitInByteUnSet(value, 8, 2))

  def isCVMRequired: Boolean = isBitSetInByte(value, 7, 2)

  def withCVMRequiredSet: TerminalTransactionQualifiers =
    TerminalTransactionQualifiers(withBitInByteSet(value, 7, 2))

  def withCVMRequiredUnSet: ApplicationInterchangeProfile =
    ApplicationInterchangeProfile(withBitInByteUnSet(value, 7, 2))

  def isIssuerUpdateProcessingSupported: Boolean = isBitSetInByte(value, 8, 3)

  def withIssuerUpdateProcessingSupportedSet: TerminalTransactionQualifiers =
    TerminalTransactionQualifiers(withBitInByteSet(value, 8, 3))

  def withIssuerUpdateProcessingSupportedUnSet: ApplicationInterchangeProfile =
    ApplicationInterchangeProfile(withBitInByteUnSet(value, 8, 3))

  def isMobileFunctionalitySupported: Boolean = isBitSetInByte(value, 7, 3)

  def withMobileFunctionalitySupportedSet: TerminalTransactionQualifiers =
    TerminalTransactionQualifiers(withBitInByteSet(value, 7, 3))

  def withMobileFunctionalitySupportedUnSet: ApplicationInterchangeProfile =
    ApplicationInterchangeProfile(withBitInByteUnSet(value, 7, 3))


}

object TerminalTransactionQualifiers extends EMVDefaultBinaryWithLengthSpec[TerminalTransactionQualifiers] {

  val tag = berTag"9F66"

  def parser = parseEMVBySpec(TerminalTransactionQualifiers, parseB(_))

  def apply(): TerminalTransactionQualifiers = TerminalTransactionQualifiers(hex"00000000")

  override val length = 4
}
