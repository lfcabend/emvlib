package org.emv.commands

import org.emv.tlv.EMVTLV.EMVTLVType
import org.emv.tlv.{CommandTemplate, ProcessingOptionsDataObjectList}
import org.iso7816.APDU
import org.iso7816.APDU.APDUCommand
import org.lau.tlv.ber._
import scodec.bits.ByteVector
import scodec.bits._
/**
  * Created by lau on 12/13/16.
  */
case class GPOCommand(commandTemplate: CommandTemplate)
  extends APDUCommand(GPOCommand.CLA,
    GPOCommand.INS, 0.toByte,
    0.toByte,
    Some(commandTemplate.serializeTLV),
    Some(0.toByte)) {

}

object GPOCommand {

  val INS: Byte = 0xA8.toByte

  val CLA: Byte = 0x80.toByte

  def apply(pdol: ProcessingOptionsDataObjectList, tagList: List[BerTLV]) =
    new GPOCommand(CommandTemplate(pdol.createDOLValue(tagList)))

  def apply() = new GPOCommand(CommandTemplate(ByteVector.empty))

}
