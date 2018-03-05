package org.lau.visa.dataelements

import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.FileControlInformationIssuerDiscretionaryData
import org.lau.tlv.ber._
import scodec.bits.ByteVector

case class ApplicationProgramID(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = ApplicationProgramID.tag
  override val templates: Set[BerTag] = Set(FileControlInformationIssuerDiscretionaryData.tag)
}

object ApplicationProgramID extends EMVDefaultBinaryWithVarLengthSpec[ApplicationProgramID] {

  val tag = berTag"9F5A"

  def parser = parseEMVBySpec(ApplicationProgramID, parseB(_))

  override val maxLength: Int = 16
  override val minLength: Int = 5
}
