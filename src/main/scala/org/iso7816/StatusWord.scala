package org.iso7816

import org.tlv.HexUtils._

/**
  * Created by lau on 6/17/16.
  */
trait StatusWordT {

  override def toString(): String = s"${stringMessage()}: ${serialize.toHex}"

  def stringMessage(): String = "StatusWord"

  val sw1: Byte

  val sw2: Byte

  def serialize: Seq[Byte] =  List(sw1, sw2)

}


trait WarningStatusWord62T extends StatusWordT {

  val sw1: Byte = 0x62.toByte

  def preFixMessage = "State of non-volatile memory is unchanged"

}

object WarningNonVolatileUnchangedEndOfFile extends WarningStatusWord62T {

  val sw2: Byte = 0x82.toByte

  override def stringMessage(): String = s"${preFixMessage}, End of file or record reached before reading N e bytes"

}


object WarningNonVolatileUnchangedDeactivated extends WarningStatusWord62T {

  val sw2: Byte = 0x83.toByte

  override def stringMessage(): String = s"${preFixMessage}, Selected file deactivated"

}


object WarningNonVolatileUnchangedFormatError extends WarningStatusWord62T {

  val sw2: Byte = 0x84.toByte

  override def stringMessage(): String = s"${preFixMessage}, File control information not formatted correctly"

}


object WarningNonVolatileUnchangedTerminatedState extends WarningStatusWord62T {

  val sw2: Byte = 0x85.toByte

  override def stringMessage(): String = s"${preFixMessage}, Selected file in termination state"

}


object WarningNonVolatileUnchangedNoInput extends WarningStatusWord62T {

  val sw2: Byte = 0x86.toByte

  override def stringMessage(): String = s"${preFixMessage}, No input data available from a sensor on the card"

}



object WarningNonVolatileUnchangedPartCurrupted extends WarningStatusWord62T {

  val sw2: Byte = 0x81.toByte

  override def stringMessage(): String = s"${preFixMessage}, Part of returned data may be corrupted"

}


object WarningNonVolatileUnchangedNoFurtherInfo extends WarningStatusWord62T {

  val sw2: Byte = 0x00.toByte

  override def stringMessage(): String = preFixMessage

}


trait WarningStatusWord63T {

  val sw1: Byte = 0x63

}

trait ErrorStatusWord64T {

  val sw1: Byte = 0x64

}

trait ErrorStatusWord65T {

  val sw1: Byte = 0x65

}

trait ErrorStatusWord68T {

  val sw1: Byte = 0x68

}

trait ErrorStatusWord69T {

  val sw1: Byte = 0x69

}

trait ErrorStatusWord6AT {

  val sw1: Byte = 0x6A

}

trait NoInfo {

  val sw2: Byte = 0x00

}

trait InCorrectParams {

  val sw2: Byte = 0x80.toByte

}

trait FunctionNotSupported {

  val sw2: Byte = 0x81.toByte

}

trait FileOrAppNotFound {

  val sw2: Byte = 0x82.toByte

}


case class StatusWord(override val sw1: Byte, override val sw2: Byte) extends StatusWordT

object NoPrecisDiagnosis extends StatusWordT {

  val sw1 = 0x6F.toByte

  val sw2 = 0x00.toByte

  override def stringMessage() = "No precise diagnosis"

}


object ClassNotSupported extends StatusWordT {

  val sw1 = 0x6E.toByte

  val sw2 = 0x00.toByte

  override def stringMessage() = "Class not supported"

}

object InstructionNotSupported extends StatusWordT {

  val sw1 = 0x6D.toByte

  val sw2 = 0x00.toByte

  override def stringMessage() = "Instruction code not supported or invalid"

}

object WrongParameters extends StatusWordT {

  val sw1 = 0x6B.toByte

  val sw2 = 0x00.toByte

  override def stringMessage() = "Wrong parameters P1-P2s"

}

object WrongLength extends StatusWordT {

  val sw1 = 0x67.toByte

  val sw2 = 0x00.toByte

  override def stringMessage() = "Wrong length; no further indication"

}

object NormalProcessingNoFurtherQualification extends StatusWordT {

  val sw1 = 0x90.toByte

  val sw2 = 0x00.toByte

  override def stringMessage() = "Normal Processing No Further Qualification"

}

case class MoreAvailable(val sw2: Byte) extends StatusWordT {

  val sw1 = MoreAvailable.sw1

  val available: Int = sw2 & 0xFF

  override def stringMessage() = s"More data available; ${available} bytes are available"

}

object MoreAvailable {

  val sw1 = 0x61.toByte

}

case class WrongLE(val sw2: Byte) extends StatusWordT {

  val sw1 = WrongLE.sw1

  val available: Int = sw2 & 0xFF

  override def stringMessage() = s"Wrong Le field; ${available} bytes are available"

}

object WrongLE {

  val sw1 = 0x6C.toByte

}


