package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._
import scodec.bits.ByteVector

case class TerminalCapabilities(override val value: ByteVector)
  extends EMVTLVLeafNTextable {

  override val tag = TerminalCapabilities.tag

}

object TerminalCapabilities extends EMVDefaultBinaryWithLengthSpec[TerminalCapabilities] {

  val tag = berTag"9F33"

  val length = 3

  def parser = parseEMVBySpec(TerminalCapabilities, parseB(_))

}
