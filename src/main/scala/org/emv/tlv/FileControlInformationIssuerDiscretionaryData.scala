package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVTLVType, Template, TemplateSpec, ValueDataType}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/7/16.
  */
case class FileControlInformationIssuerDiscretionaryData  (constructedValue: List[EMVTLVType])
  extends Template {

  override val tag: BerTag = FileControlInformationIssuerDiscretionaryData.tag

  override val templateTags: Set[BerTag] = FileControlInformationIssuerDiscretionaryData.templateTags

}

object FileControlInformationIssuerDiscretionaryData extends TemplateSpec[FileControlInformationIssuerDiscretionaryData] {

  override lazy val length: Int = 252

  val tag: BerTag = "A5"

  val templateTags: Set[BerTag] = Set()

  override val valueDataType: ValueDataType.Value = ValueDataType.B

}
