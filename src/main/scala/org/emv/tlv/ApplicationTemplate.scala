package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.tlv.TLV.{BerTLV, BerTag, BerTLVConsT}

/**
  * Created by lau on 6/8/16.
  */
case class ApplicationTemplate(constructedValue: List[EMVTLVType])
  extends Template {

  override val tag: BerTag = ApplicationTemplate.tag

  override val templateTags: Set[BerTag] = ApplicationTemplate.templateTags

}

object ApplicationTemplate extends TemplateSpec[ApplicationTemplate] {

  override lazy val length: Int = 252

  val tag: BerTag = "61"

  val templateTags: Set[BerTag] = Set(ApplicationPriorityIndicator.tag)

  override val valueDataType: ValueDataType.Value = ValueDataType.B

//  override def parseEMVTLVValue(length: Int): ApplicationTemplate.Parser[List[EMVTLVType]] = ???
}

