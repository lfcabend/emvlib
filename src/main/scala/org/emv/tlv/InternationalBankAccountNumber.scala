package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/7/16.
  */
case class InternationalBankAccountNumber(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = InternationalBankAccountNumber.tag

  override val templates = Set(FileControlInformationIssuerDiscretionaryData.tag,
    DirectoryDiscretionaryTemplate.tag)

}

object InternationalBankAccountNumber extends EMVDefaultBinaryWithVarLengthSpec[InternationalBankAccountNumber] {

  val tag = berTag"5F53"

  override val maxLength = 34

  override val minLength = 0

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(InternationalBankAccountNumber, parseB(_))

}
