package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 12/12/16.
  */
case class FileControlInformationIssuerDiscretionaryData (constructedValue: List[BerTLV])
  extends Template with TemplateTag {

  override val tag = FileControlInformationIssuerDiscretionaryData.tag

  override def copyByConstructedValue(newConstructedValue: List[BerTLV]) =
    copy(constructedValue = newConstructedValue)

  override val templates= Set(FileControlInformationProprietaryTemplate.tag)

}

object FileControlInformationIssuerDiscretionaryData extends TemplateSpec[FileControlInformationIssuerDiscretionaryData] {

  val tag = berTag"BF0C"

  override val valueDataType = ValueDataType.B
  override val maxLength = 252
  override val minLength = 0

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser(parser: Parser[EMVTLVType]) =
    parseEMVBySpec(FileControlInformationIssuerDiscretionaryData,
      parseTemplateValue(parser)(FileControlInformationIssuerDiscretionaryData)(_))

}

