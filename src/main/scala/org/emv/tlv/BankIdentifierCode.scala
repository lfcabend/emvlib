package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithVarLengthSpec, EMVDefaultAlphaNumericWithVarLengthSpec, EMVDefaultAlphaNumericWithLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 6/16/16.
  */
case class BankIdentifierCode(override val value:Seq[Byte])
  extends EMVTLVLeaf {

  override val tag: BerTag = AuthorisationCode.tag

}

object BankIdentifierCode extends EMVDefaultBinaryWithVarLengthSpec[BankIdentifierCode] {

  val tag: BerTag = "5F54"

  override val maxLength: Int = 11

  override val minLength: Int = 8
}
