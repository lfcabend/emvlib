package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVAlphaNumericSpecialWithVarLengthSpec, EMVTLVLeafTextable}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/28/16.
  */
case class MerchantNameLocation(override val value: ByteVector) extends EMVTLVLeafTextable {

  override val tag: BerTag = MerchantNameLocation.tag

}

object MerchantNameLocation extends EMVAlphaNumericSpecialWithVarLengthSpec[ByteVector, MerchantNameLocation] {

  val tag: BerTag = berTag"9F4E"

  override val maxLength: Int = Int.MaxValue

  override val minLength: Int = 0
  override val max: Int = Int.MaxValue
  override val min: Int = 0

  def parser: Parser[MerchantNameLocation] =
    parseEMVBySpec(MerchantNameLocation, parseANS(MerchantNameLocation)(_))

}

