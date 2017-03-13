package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/7/16.
  */
case class FileControlInformationProprietaryTemplate(constructedValue: List[BerTLV])
  extends Template with TemplateTag {

  override val tag = FileControlInformationProprietaryTemplate.tag

  override def copyByConstructedValue(newConstructedValue: List[BerTLV])=
    copy(constructedValue = newConstructedValue)

  override val templates= Set(FileControlInformationTemplate.tag)

}

object FileControlInformationProprietaryTemplate extends TemplateSpec[FileControlInformationProprietaryTemplate] {

  val tag = berTag"A5"

  override val valueDataType = ValueDataType.B
  override val maxLength = 252
  override val minLength = 0

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser(parser: Parser[EMVTLVType]) = parseEMVBySpec(FileControlInformationProprietaryTemplate,
    parseTemplateValue(parser)(FileControlInformationProprietaryTemplate)(_))

}
