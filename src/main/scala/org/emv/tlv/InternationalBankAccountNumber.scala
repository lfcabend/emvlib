package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithVarLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/7/16.
  */
case class InternationalBankAccountNumber(override val value: ByteVector)
  extends EMVTLVLeaf {

  override val tag: BerTag = InternationalBankAccountNumber.tag

}

object InternationalBankAccountNumber extends EMVDefaultBinaryWithVarLengthSpec[InternationalBankAccountNumber] {

  val tag: BerTag = berTag"5F53"

  override val maxLength: Int = 34

  override val minLength: Int = 0

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[InternationalBankAccountNumber] =
    parseEMVBySpec(InternationalBankAccountNumber, parseB(_))



}
