package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/27/16.
  */
case class CardholderVerificationMethodResults(override val value: ByteVector) extends EMVTLVLeaf {

  override val tag: BerTag = CardholderVerificationMethodResults.tag

}

object CardholderVerificationMethodResults extends EMVDefaultBinaryWithLengthSpec[CardholderVerificationMethodResults] {

  override val tag: BerTag = berTag"9F34"

  override val length: Int = 3

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[CardholderVerificationMethodResults] =
    parseEMVBySpec(CardholderVerificationMethodResults, parseB(_))
}