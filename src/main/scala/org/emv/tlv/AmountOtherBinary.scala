package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf, EMVTLVParser}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/1/16.
  */
case class AmountOtherBinary(override val value: ByteVector) extends EMVTLVLeaf {

  require(value.length == AmountOtherBinary.length)

  override val tag = AmountOtherBinary.tag

}

object AmountOtherBinary extends EMVDefaultBinaryWithLengthSpec[AmountOtherBinary] {

  val length = 4

  val tag = berTag"9F04"

  import fastparse.byte.all.Parser
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(AmountOtherBinary, parseB(_))

}


