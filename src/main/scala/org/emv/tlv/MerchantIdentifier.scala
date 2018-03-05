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

  override val tag = MerchantIdentifier.tag

}

object MerchantIdentifier extends EMVAlphaNumericSpecialWithLengthSpec[ByteVector, MerchantIdentifier] {

  val tag = berTag"9F16"
  override val length = 15
  override val max = 15
  override val min = 15

  def parser = parseEMVBySpec(MerchantIdentifier, parseB(_))

}


