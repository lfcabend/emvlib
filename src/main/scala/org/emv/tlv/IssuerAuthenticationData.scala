package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithVarLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._
/**
  * Created by lau on 11/7/16.
  */
case class IssuerAuthenticationData(override val value: ByteVector)
  extends EMVTLVLeaf {

  override val tag: BerTag = IssuerAuthenticationData.tag

}

object IssuerAuthenticationData extends EMVDefaultBinaryWithVarLengthSpec[IssuerAuthenticationData] {

  val tag: BerTag = berTag"91"

  override val maxLength: Int = 16

  override val minLength: Int = 8

  def parser: Parser[IssuerAuthenticationData] =
    parseEMVBySpec(IssuerAuthenticationData, parseB(_))


}
