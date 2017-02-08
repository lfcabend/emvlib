package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVBinaryWithLengthSpec, EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf, EMVTLVParser}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/2/16.
  */
case class AmountReferenceCurrency(override val value: ByteVector) extends EMVTLVLeaf {

  override val tag: BerTag = AmountReferenceCurrency.tag

}

object AmountReferenceCurrency extends EMVDefaultBinaryWithLengthSpec[AmountReferenceCurrency] {

  val tag: BerTag = berTag"9F3A"

  val length: Int = 4

  import fastparse.byte.all.Parser
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parseAmountReferenceCurrency: Parser[AmountReferenceCurrency] =
    parseEMVBySpec(AmountReferenceCurrency, parseB(_))

}