package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/8/16.
  */
case class ApplicationTemplate(constructedValue: List[BerTLV])
  extends Template with TemplateTag {

  override val tag = ApplicationTemplate.tag

  override def copyByConstructedValue(newConstructedValue: List[BerTLV]) =
    copy(constructedValue = newConstructedValue)

  override val templates = Set(FileControlInformationIssuerDiscretionaryData.tag)
}

object ApplicationTemplate extends TemplateSpec[ApplicationTemplate] {

  val tag = berTag"61"

  override val valueDataType = ValueDataType.B

  override val maxLength = 256
  override val minLength = 0

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser(parser: Parser[EMVTLVType])  = parseEMVBySpec[List[BerTLV], ApplicationTemplate](ApplicationTemplate,
      parseTemplateValue(parser)(ApplicationTemplate)(_))

}

