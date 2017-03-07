package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._
import scodec.bits.ByteVector

/**
  * Created by lau on 2/17/17.
  */
case class TerminalType(override val value: ByteVector)
  extends EMVTLVLeafNTextable {

  override val tag = TerminalType.tag

}

object TerminalType extends EMVDefaultBinaryWithLengthSpec[TerminalType] {

  val tag = berTag"9F35"

  def parser = parseEMVBySpec(TerminalType, parseB(_))

  override val length = 1

}