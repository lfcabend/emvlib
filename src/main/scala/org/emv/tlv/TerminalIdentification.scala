package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._
import scodec.bits.ByteVector

/**
  * Created by lau on 2/17/17.
  */
case class TerminalIdentification (override val value: ByteVector)
  extends EMVTLVLeaf {

  override val tag: BerTag = TerminalIdentification.tag

}

object TerminalIdentification extends EMVDefaultAlphaNumericWithLengthSpec[TerminalIdentification] {

  val tag = berTag"9F1C"

  val length = 8

  def parser = parseEMVBySpec(TerminalIdentification, parseB(_))

  override val max = 8
  override val min = 8

}