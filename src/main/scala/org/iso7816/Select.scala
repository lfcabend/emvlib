package org.iso7816

import org.emv.tlv.EMVTLV.{EMVTLVParser, EMVTLVType}
import org.iso7816.APDU.{APDUCommand, APDUCommandResponse, ParamContainer}

/**
  * Created by Lau on 4/25/2016.
  */
case class Select(val cla: APDU.Class, val p1: ParamContainer, val p2: ParamContainer, val aid: AID) extends
  APDUCommand(cla, APDU.SELECT, p1.value, 0.toByte, Some(aid.value), Some(0.toByte)) {


}

case class SelectResponse(val template: Option[EMVTLVType], override val statusWord: StatusWordT) extends
  APDUCommandResponse(template.map(_.value), statusWord) {

}

object SelectResponse {

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser(parser: Parser[EMVTLVType]) : Parser[SelectResponse] = for {
    t <- (parseEMVTLV(parser).?)
    st <- StatusWord.parseStatusWord
    _ <- fastparse.byte.all.End
  } yield (SelectResponse(t, st))

}

object Select {

  def selectDFFirstOccurenceWithFCIResponse(aid: AID) =
    Select(0.toByte, P1SelectDFName, FirstOrOnlyOccurrenceWithFCITemplate, aid)

}


object P1SelectMFDFEF extends ParamContainer {

  override def toString = "Select MFDFEF"

  val value = 0x00.toByte
}
object P1SelectChildDF extends ParamContainer {

  override def toString = "Select ChildDF"

  val value = 0x01.toByte
}
object P1SelectEFUnderCurrent extends ParamContainer {

  override def toString = "Select EF Under Current"

  val value = 0x02.toByte
}
object P1SelectParentDF extends ParamContainer {

  override def toString = "Select Parent DF"

  val value = 0x03.toByte
}

object P1SelectDFName extends ParamContainer {

  override def toString = "Select DF Name"

  val value = 0x04.toByte
}
object P1SelectFromMF extends ParamContainer {

  override def toString = "Select from MF"

  val value = 0x08.toByte
}
object P1SelectFromDF extends ParamContainer {

  override def toString = "Select from DF"

  val value = 0x09.toByte
}

trait FileOccurrence {

  val occurrence: Byte

}

trait FirstOrOnlyOccurrence extends FileOccurrence {

  val occurrence: Byte = 0x00

  override def toString = "First or Only Occurrence"

}

trait LastOccurrence extends FileOccurrence {

  val occurrence: Byte = 0x01

  override def toString = "Last Occurrence"

}


trait NextOccurrence extends FileOccurrence {

  val occurrence: Byte = 0x02

  override def toString = "Next Occurrence"

}

trait PreviousOccurrence extends FileOccurrence {

  val occurrence: Byte = 0x02


  override def toString = "Previous Occurrence"


}

trait FileControlInformation {

  val info: Byte

}

trait ReturnFCITemplate extends FileControlInformation {

  val info: Byte = 0x00


  override def toString = "Return FCI Template"

}


trait ReturnFCPTemplate extends FileControlInformation {

  val info: Byte = 0x01

  override def toString = "Return FCP Template"

}

trait ReturnFMDTemplate extends FileControlInformation {

  val info: Byte = 0x02

  override def toString = "Return FMD Template"


}


trait NoResponseOrProprietary extends FileControlInformation {


  val info: Byte = 0x03

  override def toString = "No response or proprietary"


}



trait SelectP2 extends FileControlInformation with FileOccurrence with ParamContainer {

  val value: Byte = (info | occurrence).toByte

}

object FirstOrOnlyOccurrenceWithFCITemplate extends SelectP2 with FirstOrOnlyOccurrence with ReturnFCITemplate {

    override def toString = "First or Only Occurrence/Return FCI Template"

}
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