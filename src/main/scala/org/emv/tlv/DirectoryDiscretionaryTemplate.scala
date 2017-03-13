package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/7/16.
  */
case class DirectoryDiscretionaryTemplate (constructedValue: List[BerTLV])
  extends Template with TemplateTag {

  override val tag = DirectoryDiscretionaryTemplate.tag

  override def copyByConstructedValue(newConstructedValue: List[BerTLV]): BerTLVConsT =
    copy(constructedValue = newConstructedValue)

  override val templates = Set(ApplicationTemplate.tag)
}

object DirectoryDiscretionaryTemplate extends TemplateSpec[DirectoryDiscretionaryTemplate] {

  val tag = berTag"73"

  override val valueDataType: ValueDataType.Value = ValueDataType.B
  override val maxLength = 252
  override val minLength = 0

  def parser(parser: Parser[EMVTLVType]) = parseEMVBySpec(DirectoryDiscretionaryTemplate,
    parseTemplateValue(parser)(DirectoryDiscretionaryTemplate)(_))

}
