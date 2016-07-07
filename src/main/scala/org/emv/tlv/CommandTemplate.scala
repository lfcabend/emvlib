package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithVarLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 6/30/16.
  */
case class CommandTemplate(override val value: Seq[Byte]) extends EMVTLVLeaf {
  override val tag: BerTag = CommandTemplate.tag
}

object CommandTemplate extends EMVDefaultBinaryWithVarLengthSpec[CommandTemplate] {
  override val maxLength: Int = Int.MaxValue
  override val minLength: Int = 0
  override val tag: BerTag = "83"
}
