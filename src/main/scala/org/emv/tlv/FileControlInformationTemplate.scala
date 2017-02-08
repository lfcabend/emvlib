package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVTLVType, Template, TemplateSpec, ValueDataType}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/7/16.
  */
case class FileControlInformationTemplate (constructedValue: List[BerTLV])
  extends Template {

  override val tag: BerTag = FileControlInformationTemplate.tag

  override val templateTags: Set[BerTag] = FileControlInformationTemplate.templateTags

  override def copyByConstructedValue(newConstructedValue: List[BerTLV]): BerTLVConsT =
    copy(constructedValue = newConstructedValue)
}

object FileControlInformationTemplate extends TemplateSpec[FileControlInformationTemplate] {

  val tag: BerTag = berTag"6F"

  val templateTags: Set[BerTag] = Set(DedicatedFileName.tag, FileControlInformationProprietaryTemplate.tag)

  override val valueDataType: ValueDataType.Value = ValueDataType.B
  override val maxLength: Int = 252
  override val minLength: Int = 0

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[FileControlInformationTemplate] =
    parseEMVBySpec(FileControlInformationTemplate, parseTemplateValue(FileControlInformationTemplate)(_))


}

