package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/7/16.
  */
case class IssuerActionCodeDenial (override val value: ByteVector)
  extends EMVTLVLeaf {

  override val tag: BerTag = IssuerActionCodeDenial.tag

}

object IssuerActionCodeDenial extends EMVDefaultBinaryWithLengthSpec[IssuerActionCodeDenial] {

  val tag: BerTag = berTag"9F0E"

  override val length: Int = 5

  def parser: Parser[IssuerActionCodeDenial] =
    parseEMVBySpec(IssuerActionCodeDenial, parseB(_))


}