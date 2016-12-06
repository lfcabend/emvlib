package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVTLVType, Template, TemplateSpec, ValueDataType}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/28/16.
  */
case class ResponseMessageTemplateFormat1(constructedValue: List[EMVTLVType])
  extends Template {

  override val tag: BerTag = ResponseMessageTemplateFormat1.tag

  override val templateTags: Set[BerTag] = ResponseMessageTemplateFormat1.templateTags

}

object ResponseMessageTemplateFormat1 extends TemplateSpec[ResponseMessageTemplateFormat1] {

  override lazy val length: Int = 252

  val tag: BerTag = "80"

  val templateTags: Set[BerTag] = Set()

  override val valueDataType: ValueDataType.Value = ValueDataType.B

}
