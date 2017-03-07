package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/6/16.
  */
case class ApplicationPriorityIndicator(override val value: ByteVector)
  extends EMVTLVLeaf with BinaryNumber with TemplateTag {

  override val tag = ApplicationPriorityIndicator.tag

  override val templates = Set(ApplicationTemplate.tag,
    FileControlInformationProprietaryTemplate.tag)
}

object ApplicationPriorityIndicator extends EMVDefaultBinaryWithLengthSpec[ApplicationPriorityIndicator] {

  val tag = berTag"87"

  val length = 1

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(ApplicationPriorityIndicator, parseB(_))

}