package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/17/16.
  */
case class CardholderName(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag with Textable {

  override val tag = CardholderName.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)

}

object CardholderName extends EMVDefaultAlphaNumericSpecialWithVarLengthSpec[CardholderName] {

  val tag = berTag"5F20"

  override val maxLength = 26

  override val minLength = 2
  override val max = 26
  override val min = 2

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(CardholderName, parseANS(CardholderName)(_))
}
