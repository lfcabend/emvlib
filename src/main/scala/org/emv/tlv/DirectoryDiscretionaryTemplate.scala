package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVTLVType, Template, TemplateSpec, ValueDataType}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/7/16.
  */
case class DirectoryDiscretionaryTemplate (constructedValue: List[EMVTLVType])
  extends Template {

  override val tag: BerTag = DirectoryDiscretionaryTemplate.tag

  override val templateTags: Set[BerTag] = DirectoryDiscretionaryTemplate.templateTags

}

object DirectoryDiscretionaryTemplate extends TemplateSpec[DirectoryDiscretionaryTemplate] {

  override lazy val length: Int = 252

  val tag: BerTag = "73"

  val templateTags: Set[BerTag] = Set()

  override val valueDataType: ValueDataType.Value = ValueDataType.B

}
