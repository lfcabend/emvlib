package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVBinaryWithLengthSpec, EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf, EMVTLVParser}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/2/16.
  */
case class AmountReferenceCurrency(override val value: ByteVector) extends EMVTLVLeaf {

  override val tag = AmountReferenceCurrency.tag

}

object AmountReferenceCurrency extends EMVDefaultBinaryWithLengthSpec[AmountReferenceCurrency] {

  val tag = berTag"9F3A"

  val length = 4

  import fastparse.byte.all.Parser
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(AmountReferenceCurrency, parseB(_))

}