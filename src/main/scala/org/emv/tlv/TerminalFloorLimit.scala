package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._
import scodec.bits.ByteVector

/**
  * Created by lau on 2/17/17.
  */
case class TerminalFloorLimit(override val value: ByteVector)
  extends EMVTLVLeafNTextable {

  override val tag = TerminalFloorLimit.tag

}

object TerminalFloorLimit extends EMVDefaultBinaryWithLengthSpec[TerminalFloorLimit] {

  val tag = berTag"9F1B"

  val length = 4

  def parser = parseEMVBySpec(TerminalFloorLimit, parseB(_))

}