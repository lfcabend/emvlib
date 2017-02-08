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

  override val tag: BerTag = ResponseMessageTemplateFormat2.tag

  override val templateTags: Set[BerTag] = ResponseMessageTemplateFormat2.templateTags

  override def copyByConstructedValue(newConstructedValue: List[BerTLV]): BerTLVConsT =
    copy(constructedValue = newConstructedValue)
}

object ResponseMessageTemplateFormat2 extends TemplateSpec[ResponseMessageTemplateFormat2] {

  val tag: BerTag = berTag"77"

  val templateTags: Set[BerTag] = Set()

  override val valueDataType: ValueDataType.Value = ValueDataType.B
  override val maxLength: Int = 252
  override val minLength: Int = 0

  def parser: Parser[ResponseMessageTemplateFormat2] =
    parseEMVBySpec(ResponseMessageTemplateFormat2, parseTemplateValue(ResponseMessageTemplateFormat2)(_))

}
