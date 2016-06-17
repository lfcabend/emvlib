package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultNumericWithLengthSpec, EMVNumericWithLengthSpec, EMVTLVLeaf, LeafToStringHelper}
import org.tlv.TLV.{BerTag, BerTLVLeafT}

/**
  * Created by lau on 6/1/16.
  */


trait AmountAuthorizedT extends EMVTLVLeaf {

  override val tag: BerTag = AmountAuthorized.tag

}

case class AmountAuthorized (override val value: Seq[Byte]) extends AmountAuthorizedT {

  require(value.length == AmountAuthorized.length)

}


trait AmountAuthorizedSpec extends EMVDefaultNumericWithLengthSpec[AmountAuthorized] {

  val length = 6

  val tag: BerTag = "9F02"

  override val max: Int = 12

  override val min: Int = 12

}

object AmountAuthorized extends AmountAuthorizedSpec

