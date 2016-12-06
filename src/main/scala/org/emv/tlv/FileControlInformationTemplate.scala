package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVTLVType, Template, TemplateSpec, ValueDataType}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/7/16.
  */
case class FileControlInformationTemplate (constructedValue: List[EMVTLVType])
  extends Template {

  override val tag: BerTag = FileControlInformationTemplate.tag

  override val templateTags: Set[BerTag] = FileControlInformationTemplate.templateTags

}

object FileControlInformationTemplate extends TemplateSpec[FileControlInformationTemplate] {

  override lazy val length: Int = 252

  val tag: BerTag = "6F"

  val templateTags: Set[BerTag] = Set()

  override val valueDataType: ValueDataType.Value = ValueDataType.B

}
