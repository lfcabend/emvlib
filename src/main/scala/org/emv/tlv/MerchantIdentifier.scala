package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVAlphaNumericSpecialWithLengthSpec, EMVTLVLeafTextable}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/28/16.
  */
case class MerchantIdentifier(override val value: ByteVector) extends EMVTLVLeafTextable {

  override val tag: BerTag = MerchantIdentifier.tag

}

object MerchantIdentifier extends EMVAlphaNumericSpecialWithLengthSpec[ByteVector, MerchantIdentifier] {

  val tag: BerTag = berTag"9F16"
  override val length: Int = 15
  override val max: Int = 15
  override val min: Int = 15

  def parser: Parser[MerchantIdentifier] =
    parseEMVBySpec(MerchantIdentifier, parseB(_))

}


