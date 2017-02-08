package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVDefaultNumericWithLengthSpec, EMVTLVLeafNTextable}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/17/16.
  */
case class MerchantCategoryCode(override val value: ByteVector)
  extends EMVTLVLeafNTextable {

  override val tag: BerTag = MerchantCategoryCode.tag

}

object MerchantCategoryCode extends EMVDefaultNumericWithLengthSpec[MerchantCategoryCode] {

  val tag: BerTag = berTag"9F15"

  val length: Int = 2

  override val max: Int = 4

  override val min: Int = 4

  def parser: Parser[MerchantCategoryCode] =
    parseEMVBySpec(MerchantCategoryCode, parseB(_))

}
