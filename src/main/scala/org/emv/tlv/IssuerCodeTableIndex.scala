package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVDefaultNumericWithLengthSpec, EMVTLVLeafNTextable}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/7/16.
  */
case class IssuerCodeTableIndex (override val value: ByteVector)
  extends EMVTLVLeafNTextable with TextableNumber {

  override val tag: BerTag = IssuerCodeTableIndex.tag

}

object IssuerCodeTableIndex extends EMVDefaultNumericWithLengthSpec[IssuerCodeTableIndex] {

  val tag: BerTag = berTag"9F11"

  val length: Int = 1

  override val max: Int = 2

  override val min: Int = 2

  def parser: Parser[IssuerCodeTableIndex] =
    parseEMVBySpec(IssuerCodeTableIndex, parseB(_))


}

