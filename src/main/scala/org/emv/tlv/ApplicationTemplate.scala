package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVTLVType, _}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/8/16.
  */
case class ApplicationTemplate(constructedValue: List[BerTLV])
  extends Template with TemplateTag {

  override val tag: BerTag = ApplicationTemplate.tag

  override val templateTags: Set[BerTag] = ApplicationTemplate.templateTags

  override def copyByConstructedValue(newConstructedValue: List[BerTLV]): BerTLVConsT =
    copy(constructedValue = newConstructedValue)

  override val templates: Set[BerTag] = Set(FileControlInformationIssuerDiscretionaryData.tag)
}

object ApplicationTemplate extends TemplateSpec[ApplicationTemplate] {

  val tag: BerTag = berTag"61"

  val templateTags: Set[BerTag] = Set(ApplicationPriorityIndicator.tag,
    ApplicationDedicatedFileName.tag, ApplicationLabel.tag)

  override val valueDataType: ValueDataType.Value = ValueDataType.B

  override val maxLength: Int = 256
  override val minLength: Int = 0

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec[List[BerTLV], ApplicationTemplate](ApplicationTemplate,
      parseTemplateValue(ApplicationTemplate)(_))

}

