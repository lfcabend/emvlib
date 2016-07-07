package org.iso7816

import org.emv.tlv.EMVTLV.EMVTLVType
import org.iso7816.APDU.{APDUCommand, APDUCommandResponse, ParamContainer}

/**
  * Created by Lau on 4/25/2016.
  */
case class Select(val cla: APDU.Class, val p1: ParamContainer, val p2: ParamContainer, val aid: AID) extends
  APDUCommand(cla, APDU.SELECT, p1.value, 0.toByte, Some(aid.value), None) {


}

case class SelectResponse(val template: Option[EMVTLVType], val statusWord: StatusWordT) extends
  APDUCommandResponse(template.map(_.value), statusWord) {

}

object Select {

  def selectDFFirstOccurenceWithFCIResponse(aid: AID) =
    Select(0.toByte, P1SelectDFName, FirstOrOnlyOccurrenceWithFCITemplate, aid)

}


object P1SelectMFDFEF extends ParamContainer {
  val value = 0x00.toByte
}
object P1SelectChildDF extends ParamContainer {
  val value = 0x01.toByte
}
object P1SelectEFUnderCurrent extends ParamContainer {
  val value = 0x02.toByte
}
object P1SelectParentDF extends ParamContainer {
  val value = 0x03.toByte
}

object P1SelectDFName extends ParamContainer {
  val value = 0x04.toByte
}
object P1SelectFromMF extends ParamContainer {
  val value = 0x08.toByte
}
object P1SelectFromDF extends ParamContainer {
  val value = 0x09.toByte
}

trait FileOccurrence {

  val occurrence: Byte

}

trait FirstOrOnlyOccurrence extends FileOccurrence {

  val occurrence: Byte = 0x00

}

trait LastOccurrence extends FileOccurrence {

  val occurrence: Byte = 0x01

}


trait NextOccurrence extends FileOccurrence {

  val occurrence: Byte = 0x02

}

trait PreviousOccurrence extends FileOccurrence {

  val occurrence: Byte = 0x02

}

trait FileControlInformation {

  val info: Byte

}

trait ReturnFCITemplate extends FileControlInformation {

  val info: Byte = 0x00

}


trait ReturnFCPTemplate extends FileControlInformation {

  val info: Byte = 0x01

}

trait ReturnFMDTemplate extends FileControlInformation {

  val info: Byte = 0x02

}


trait NoResponseOrProprietary extends FileControlInformation {

  val info: Byte = 0x03

}


trait SelectP2 extends FileControlInformation with FileOccurrence with ParamContainer {

  val value: Byte = (info | occurrence).toByte

}

object FirstOrOnlyOccurrenceWithFCITemplate extends SelectP2 with FirstOrOnlyOccurrence with ReturnFCITemplate
object FirstOrOnlyOccurrenceWithFCPTemplate extends SelectP2 with FirstOrOnlyOccurrence with ReturnFCPTemplate
object FirstOrOnlyOccurrenceWithFMDTemplate extends SelectP2 with FirstOrOnlyOccurrence with ReturnFMDTemplate
object FirstOrOnlyOccurrenceWithNoResponseOrProprietary extends SelectP2 with FirstOrOnlyOccurrence with NoResponseOrProprietary
object LastOccurrenceWithFCITemplate extends SelectP2 with LastOccurrence with ReturnFCITemplate
object LastOccurrenceWithFCPTemplate extends SelectP2 with LastOccurrence with ReturnFCPTemplate
object LastOccurrenceWithFMDTemplate extends SelectP2 with LastOccurrence with ReturnFMDTemplate
object LastOccurrenceWithNoResponseOrProprietary extends SelectP2 with LastOccurrence with NoResponseOrProprietary
object NextOccurrenceWithFCITemplate extends SelectP2 with NextOccurrence with ReturnFCITemplate
object NextOccurrenceWithFCPTemplate extends SelectP2 with NextOccurrence with ReturnFCPTemplate
object NextOccurrenceWithFMDTemplate extends SelectP2 with NextOccurrence with ReturnFMDTemplate
object NextOccurrenceWithNoResponseOrProprietary extends SelectP2 with NextOccurrence with NoResponseOrProprietary
object PreviousOccurrenceWithFCITemplate extends SelectP2 with PreviousOccurrence with ReturnFCITemplate
object PreviousOccurrenceWithFCPTemplate extends SelectP2 with PreviousOccurrence with ReturnFCPTemplate
object PreviousOccurrenceWithFMDTemplate extends SelectP2 with PreviousOccurrence with ReturnFMDTemplate
object PreviousOccurrenceWithNoResponseOrProprietary extends SelectP2 with PreviousOccurrence with NoResponseOrProprietary