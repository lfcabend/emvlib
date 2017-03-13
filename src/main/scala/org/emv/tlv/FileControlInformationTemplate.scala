package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/7/16.
  */
case class FileControlInformationTemplate (constructedValue: List[BerTLV])
  extends Template {

  override val tag = FileControlInformationTemplate.tag

  override def copyByConstructedValue(newConstructedValue: List[BerTLV]) =
    copy(constructedValue = newConstructedValue)
}

object FileControlInformationTemplate extends TemplateSpec[FileControlInformationTemplate] {

  val tag = berTag"6F"

  override val valueDataType = ValueDataType.B
  override val maxLength = 252
  override val minLength = 0

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser(parser: Parser[EMVTLVType]) = parseEMVBySpec(FileControlInformationTemplate,
    parseTemplateValue(parser)(FileControlInformationTemplate)(_))


}

