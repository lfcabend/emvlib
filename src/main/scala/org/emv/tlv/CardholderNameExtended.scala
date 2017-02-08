package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVDefaultAlphaNumericSpecialWithVarLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._
/**
  * Created by lau on 6/17/16.
  */
case class CardholderNameExtended(override val value: ByteVector)
  extends EMVTLVLeaf {

  override val tag: BerTag = CardholderNameExtended.tag

}

object CardholderNameExtended extends EMVDefaultAlphaNumericSpecialWithVarLengthSpec[CardholderNameExtended] {

  val tag: BerTag = berTag"9F0B"

  override val maxLength: Int = 45

  override val minLength: Int = 27
  override val max: Int = 45
  override val min: Int = 27

  def parser: Parser[CardholderNameExtended] =
    parseEMVBySpec(CardholderNameExtended, parseANS(CardholderNameExtended)(_))

}
