package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.tlv.TLV.{BerTag, BerTLVLeafT}

/**
  * Created by lau on 6/6/16.
  */
case class ApplicationPriorityIndicator(override val value: Seq[Byte])
  extends EMVTLVLeaf with BinaryNumber with TemplateTag {

  override val tag: BerTag = ApplicationPriorityIndicator.tag

  override val templates: Set[BerTag] = Set(ApplicationTemplate.tag)
}

object ApplicationPriorityIndicator extends EMVDefaultBinaryWithLengthSpec[ApplicationPriorityIndicator] {

  val tag: BerTag = "87"

  val length: Int = 1

}