package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVTLVType, Template, TemplateSpec, ValueDataType}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/28/16.
  */
case class READRECORDResponseMessageTemplate(constructedValue: List[BerTLV])
  extends Template {

  override val tag: BerTag = READRECORDResponseMessageTemplate.tag

  override val templateTags: Set[BerTag] = READRECORDResponseMessageTemplate.templateTags

  override def copyByConstructedValue(newConstructedValue: List[BerTLV]): BerTLVConsT =
    copy(constructedValue = newConstructedValue)
}

object READRECORDResponseMessageTemplate extends TemplateSpec[READRECORDResponseMessageTemplate] {

  val tag: BerTag = berTag"70"

  val templateTags: Set[BerTag] = Set()

  override val valueDataType: ValueDataType.Value = ValueDataType.B
  override val maxLength: Int = 252
  override val minLength: Int = 0

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[READRECORDResponseMessageTemplate] =
    parseEMVBySpec(READRECORDResponseMessageTemplate, parseTemplateValue(READRECORDResponseMessageTemplate)(_))

}
