package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVTLVType, Template, TemplateSpec, ValueDataType}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/7/16.
  */
case class FileControlInformationProprietaryTemplate(constructedValue: List[BerTLV])
  extends Template {

  override val tag: BerTag = FileControlInformationProprietaryTemplate.tag

  override val templateTags: Set[BerTag] = FileControlInformationProprietaryTemplate.templateTags

  override def copyByConstructedValue(newConstructedValue: List[BerTLV]): BerTLVConsT =
    copy(constructedValue = newConstructedValue)
}

object FileControlInformationProprietaryTemplate extends TemplateSpec[FileControlInformationProprietaryTemplate] {

  val tag: BerTag = berTag"A5"

  val templateTags: Set[BerTag] = Set(FileControlInformationIssuerDiscretionaryData.tag)

  override val valueDataType: ValueDataType.Value = ValueDataType.B
  override val maxLength: Int = 252
  override val minLength: Int = 0

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[FileControlInformationProprietaryTemplate] =
    parseEMVBySpec(FileControlInformationProprietaryTemplate, parseTemplateValue(FileControlInformationProprietaryTemplate)(_))

}