package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithVarLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._
/**
  * Created by lau on 6/16/16.
  */
case class BankIdentifierCode(override val value:ByteVector)
  extends EMVTLVLeaf {

  override val tag: BerTag = AuthorisationCode.tag

}

object BankIdentifierCode extends EMVDefaultBinaryWithVarLengthSpec[BankIdentifierCode] {

  val tag: BerTag = berTag"5F54"

  override val maxLength: Int = 11

  override val minLength: Int = 8

  def parser: Parser[BankIdentifierCode] =
    parseEMVBySpec(BankIdentifierCode, parseB(_))


}
