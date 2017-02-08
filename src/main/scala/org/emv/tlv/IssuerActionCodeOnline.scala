package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/7/16.
  */
case class IssuerActionCodeOnline(override val value: ByteVector)
  extends EMVTLVLeaf {

  override val tag: BerTag = IssuerActionCodeOnline.tag

}

object IssuerActionCodeOnline extends EMVDefaultBinaryWithLengthSpec[IssuerActionCodeOnline] {

  val tag: BerTag = berTag"9F0F"

  override val length: Int = 5

  def parser: Parser[IssuerActionCodeOnline] =
    parseEMVBySpec(IssuerActionCodeOnline, parseB(_))


}

