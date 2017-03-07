package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/28/16.
  */
case class READRECORDResponseMessageTemplate(constructedValue: List[BerTLV])
  extends Template {

  override def copyByConstructedValue(newConstructedValue: List[BerTLV]) =
    copy(constructedValue = newConstructedValue)

  override val tag: BerTag = READRECORDResponseMessageTemplate.tag
}

object READRECORDResponseMessageTemplate extends TemplateSpec[READRECORDResponseMessageTemplate] {

  val tag = berTag"70"

  override val valueDataType = ValueDataType.B
  override val maxLength  = 252
  override val minLength = 0

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(READRECORDResponseMessageTemplate, parseTemplateValue(READRECORDResponseMessageTemplate)(_))

}
