package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVTLVType, Template, TemplateSpec, ValueDataType}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/28/16.
  */
case class ResponseMessageTemplateFormat2(constructedValue: List[EMVTLVType])
  extends Template {

  override val tag: BerTag = ResponseMessageTemplateFormat2.tag

  override val templateTags: Set[BerTag] = ResponseMessageTemplateFormat2.templateTags

}

object ResponseMessageTemplateFormat2 extends TemplateSpec[ResponseMessageTemplateFormat2] {

  override lazy val length: Int = 252

  val tag: BerTag = "77"

  val templateTags: Set[BerTag] = Set()

  override val valueDataType: ValueDataType.Value = ValueDataType.B

}
