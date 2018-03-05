package org.emv.commands

import org.emv.tlv.{CardRiskManagementDataObjectList1, CommandTemplate, CryptogramInformationData, ProcessingOptionsDataObjectList}
import org.iso7816.APDU.APDUCommand
import org.lau.tlv.ber.BerTLV
import scodec.bits.ByteVector

/**
  * Created by lau on 2/8/17.
  */
case class GenerateACCommand(cid: CryptogramInformationData, commandTemplate: CommandTemplate, cda: Boolean = false)
  extends APDUCommand(GenerateACCommand.CLA,
    GenerateACCommand.INS,
    GenerateACCommand.createP1FromCIDandCDA(cid, cda),
    0.toByte,
    Some(commandTemplate.serializeTLV),
    Some(0.toByte))

object GenerateACCommand {

  val INS: Byte = 0xAE.toByte
  val CLA: Byte = 0x80.toByte

  def createP1FromCIDandCDA(cid: CryptogramInformationData, cda: Boolean) =
    if (cda) (cid.value.toByte() | 0x10).toByte else cid.value.toByte()

  def apply(cid: CryptogramInformationData, cdol: CardRiskManagementDataObjectList1, tagList: List[BerTLV]) =
    new GenerateACCommand(cid, CommandTemplate(cdol.createDOLValue(tagList)))

  def apply(cid: CryptogramInformationData, cdol: CardRiskManagementDataObjectList1,
            tagList: List[BerTLV], cda: Boolean) =
    new GenerateACCommand(cid, CommandTemplate(cdol.createDOLValue(tagList)), cda)

}