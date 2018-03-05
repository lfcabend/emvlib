package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithVarLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/30/16.
  */
case class CommandTemplate(override val value: ByteVector) extends EMVTLVLeaf {

  override val tag: BerTag = CommandTemplate.tag

}

object CommandTemplate extends EMVDefaultBinaryWithVarLengthSpec[CommandTemplate] {

  override val maxLength = Int.MaxValue

  override val minLength = 0

  override val tag = berTag"83"

  def parser = parseEMVBySpec(CommandTemplate, parseB(_))


}

