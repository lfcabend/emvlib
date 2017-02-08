package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVTLVType, Template, TemplateSpec, ValueDataType}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 12/12/16.
  */
case class FileControlInformationIssuerDiscretionaryData (constructedValue: List[BerTLV])
  extends Template {

  override val tag: BerTag = FileControlInformationIssuerDiscretionaryData.tag

  override val templateTags: Set[BerTag] = FileControlInformationIssuerDiscretionaryData.templateTags

  override def copyByConstructedValue(newConstructedValue: List[BerTLV]): BerTLVConsT =
    copy(constructedValue = newConstructedValue)
}

object FileControlInformationIssuerDiscretionaryData extends TemplateSpec[FileControlInformationIssuerDiscretionaryData] {

  val tag: BerTag = berTag"BF0C"

  val templateTags: Set[BerTag] = Set(ApplicationTemplate.tag)

  override val valueDataType: ValueDataType.Value = ValueDataType.B
  override val maxLength: Int = 252
  override val minLength: Int = 0

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[FileControlInformationIssuerDiscretionaryData] =
    parseEMVBySpec(FileControlInformationIssuerDiscretionaryData, parseTemplateValue(FileControlInformationIssuerDiscretionaryData)(_))

}

