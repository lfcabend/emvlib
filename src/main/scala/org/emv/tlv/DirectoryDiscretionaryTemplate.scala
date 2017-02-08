package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVTLVType, Template, TemplateSpec, ValueDataType}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/7/16.
  */
case class DirectoryDiscretionaryTemplate (constructedValue: List[BerTLV])
  extends Template {

  override val tag: BerTag = DirectoryDiscretionaryTemplate.tag

  override val templateTags: Set[BerTag] = DirectoryDiscretionaryTemplate.templateTags

  override def copyByConstructedValue(newConstructedValue: List[BerTLV]): BerTLVConsT =
    copy(constructedValue = newConstructedValue)
}

object DirectoryDiscretionaryTemplate extends TemplateSpec[DirectoryDiscretionaryTemplate] {

  val tag: BerTag = berTag"73"

  val templateTags: Set[BerTag] = Set()

  override val valueDataType: ValueDataType.Value = ValueDataType.B
  override val maxLength: Int = 252
  override val minLength: Int = 0

  def parser: Parser[DirectoryDiscretionaryTemplate] =
    parseEMVBySpec(DirectoryDiscretionaryTemplate, parseTemplateValue(DirectoryDiscretionaryTemplate)(_))

}
