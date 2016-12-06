package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVDefaultBinaryWithVarLengthSpec, EMVTLVLeaf, TemplateTag}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/7/16.
  */
case class InternationalBankAccountNumber(override val value: Seq[Byte])
  extends EMVTLVLeaf {

  override val tag: BerTag = InternationalBankAccountNumber.tag

}

object InternationalBankAccountNumber extends EMVDefaultBinaryWithVarLengthSpec[InternationalBankAccountNumber] {

  val tag: BerTag = "5F53"

  override val maxLength: Int = 34

  override val minLength: Int = 0

}
