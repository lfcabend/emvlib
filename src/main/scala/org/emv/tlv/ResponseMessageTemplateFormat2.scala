package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVTLVType, Template, TemplateSpec, ValueDataType}
import org.lau.tlv.ber._
import scodec.bits._


/**
  * Created by lau on 11/28/16.
  */
case class ResponseMessageTemplateFormat2(constructedValue: List[BerTLV])
  extends Template {

  override val tag = ResponseMessageTemplateFormat2.tag

  override def copyByConstructedValue(newConstructedValue: List[BerTLV]) =
    copy(constructedValue = newConstructedValue)
}

object ResponseMessageTemplateFormat2 extends TemplateSpec[ResponseMessageTemplateFormat2] {

  val tag = berTag"77"

  override val valueDataType = ValueDataType.B
  override val maxLength = 252
  override val minLength = 0

  def parser(parser: Parser[EMVTLVType]) = parseEMVBySpec(ResponseMessageTemplateFormat2,
    parseTemplateValue(parser)(ResponseMessageTemplateFormat2)(_))

}
