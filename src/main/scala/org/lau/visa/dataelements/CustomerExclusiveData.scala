package org.lau.visa.dataelements

import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv._
import org.lau.tlv.ber._
import scodec.bits.ByteVector

case class CustomerExclusiveData(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = CustomerExclusiveData.tag

  override val templates: Set[BerTag] = Set(ResponseMessageTemplateFormat2.tag, READRECORDResponseMessageTemplate.tag)

}

object CustomerExclusiveData extends EMVDefaultBinaryWithVarLengthSpec[CustomerExclusiveData] {

  val tag = berTag"9F7C"

  def parser = parseEMVBySpec(CustomerExclusiveData, parseB(_))

  override val maxLength: Int = 32
  override val minLength: Int = 0

}
