package org.lau.visa.dataelements

import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv._
import org.lau.tlv.ber._
import scodec.bits._

case class TerminalTransactionQualifiers(override val value: ByteVector)
  extends EMVTLVLeaf  {

  override val tag = TerminalTransactionQualifiers.tag

}

object TerminalTransactionQualifiers extends EMVDefaultBinaryWithLengthSpec[TerminalTransactionQualifiers] {

  val tag = berTag"9F66"

  def parser = parseEMVBySpec(TerminalTransactionQualifiers, parseB(_))

  def apply(): TerminalTransactionQualifiers = TerminalTransactionQualifiers(hex"00000000")

  override val length = 4
}
