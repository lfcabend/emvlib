package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVTLVType, Template, TemplateSpec, ValueDataType}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/28/16.
  */
case class READRECORDResponseMessageTemplate(constructedValue: List[EMVTLVType])
  extends Template {

  override val tag: BerTag = READRECORDResponseMessageTemplate.tag

  override val templateTags: Set[BerTag] = READRECORDResponseMessageTemplate.templateTags

}

object READRECORDResponseMessageTemplate extends TemplateSpec[READRECORDResponseMessageTemplate] {

  override lazy val length: Int = 252

  val tag: BerTag = "70"

  val templateTags: Set[BerTag] = Set()

  override val valueDataType: ValueDataType.Value = ValueDataType.B

}
