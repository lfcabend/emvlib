package org.emv.commands

import org.emv.tlv.{CommandTemplate, ProcessingOptionsDataObjectList}
import org.iso7816.APDU.APDUCommand
import org.lau.tlv.ber.BerTLV
import scodec.bits.ByteVector

/**
  * Created by lau on 2/7/17.
  */
case class ReadRecordCommand(recordNumber: Byte, sfi: Byte) extends APDUCommand(ReadRecordCommand.CLA,
  ReadRecordCommand.INS, recordNumber,
  (0x04.toByte | (sfi << 3)).toByte,
  None,
  Some(0x00.toByte))

object ReadRecordCommand {

  val INS: Byte = org.iso7816.APDU.READ_RECORD_B2

  val CLA: Byte = 0x00.toByte

}
