package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/27/16.
  */
case class CardholderVerificationMethodResults(override val value: ByteVector) extends EMVTLVLeaf {

  override val tag = CardholderVerificationMethodResults.tag

}

object CardholderVerificationMethodResults extends EMVDefaultBinaryWithLengthSpec[CardholderVerificationMethodResults] {

  override val tag = berTag"9F34"

  override val length = 3

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(CardholderVerificationMethodResults, parseB(_))
}