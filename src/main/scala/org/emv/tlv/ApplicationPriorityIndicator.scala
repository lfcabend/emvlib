package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/6/16.
  */
case class ApplicationPriorityIndicator(override val value: ByteVector)
  extends EMVTLVLeaf with BinaryNumber with TemplateTag {

  override val tag: BerTag = ApplicationPriorityIndicator.tag

  override val templates: Set[BerTag] = Set(ApplicationTemplate.tag)
}

object ApplicationPriorityIndicator extends EMVDefaultBinaryWithLengthSpec[ApplicationPriorityIndicator] {

  val tag: BerTag = berTag"87"

  val length: Int = 1

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[ApplicationPriorityIndicator] =
    parseEMVBySpec(ApplicationPriorityIndicator, parseB(_))

}