package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._
/**
  * Created by lau on 6/17/16.
  */
case class CardholderNameExtended(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = CardholderNameExtended.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)

}

object CardholderNameExtended extends EMVDefaultAlphaNumericSpecialWithVarLengthSpec[CardholderNameExtended] {

  val tag = berTag"9F0B"

  override val maxLength = 45

  override val minLength = 27
  override val max  = 45
  override val min = 27

  def parser: Parser[CardholderNameExtended] =
    parseEMVBySpec(CardholderNameExtended, parseANS(CardholderNameExtended)(_))

}
