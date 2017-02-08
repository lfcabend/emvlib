package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultAlphaNumericSpecialWithVarLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/17/16.
  */
case class CardholderName(override val value: ByteVector)
  extends EMVTLVLeaf {

  override val tag: BerTag = CardholderName.tag

}

object CardholderName extends EMVDefaultAlphaNumericSpecialWithVarLengthSpec[CardholderName] {

  val tag: BerTag = berTag"5F20"

  override val maxLength: Int = 26

  override val minLength: Int = 2
  override val max: Int = 26
  override val min: Int = 2

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[CardholderName] =
    parseEMVBySpec(CardholderName, parseANS(CardholderName)(_))
}
