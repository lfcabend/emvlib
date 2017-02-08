package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVDefaultNumericWithLengthSpec, EMVTLVLeafNTextable}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/10/16.
  */
case class IssuerIdentificationNumber(override val value: ByteVector)
  extends EMVTLVLeafNTextable {

  override val tag: BerTag = IssuerIdentificationNumber.tag

}

object IssuerIdentificationNumber extends EMVDefaultNumericWithLengthSpec[IssuerIdentificationNumber] {

  val tag: BerTag = berTag"42"

  val length: Int = 3

  override val max: Int = 6

  override val min: Int = 6

  def parser: Parser[IssuerIdentificationNumber] =
    parseEMVBySpec(IssuerIdentificationNumber, parseB(_))


}
