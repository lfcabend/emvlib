package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 2/17/17.
  */
case class TerminalVerificationResults(override val value: ByteVector)
  extends EMVTLVLeafNTextable {

  override val tag: BerTag = TerminalVerificationResults.tag

}

object TerminalVerificationResults extends EMVDefaultBinaryWithLengthSpec[TerminalVerificationResults] {

  val tag: BerTag = berTag"95"

  def parser = parseEMVBySpec(TerminalVerificationResults, parseB(_))

  override val length: Int = 5

  def apply(): TerminalVerificationResults = new TerminalVerificationResults(hex"0000000000")

}