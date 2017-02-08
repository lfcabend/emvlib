package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithVarLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/7/16.
  */
case class IssuerApplicationData(override val value: ByteVector)
  extends EMVTLVLeaf {

  override val tag: BerTag = IssuerApplicationData.tag

}

object IssuerApplicationData extends EMVDefaultBinaryWithVarLengthSpec[IssuerApplicationData] {

  val tag: BerTag = berTag"9F10"

  override val maxLength: Int = 32

  override val minLength: Int = 0

  def parser: Parser[IssuerApplicationData] =
    parseEMVBySpec(IssuerApplicationData, parseB(_))


}
