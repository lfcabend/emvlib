package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultNumericWithLengthSpec, EMVNumericWithLengthSpec, EMVTLVLeaf, LeafToStringHelper}
import org.tlv.TLV.{BerTag, BerTLVLeafT}

/**
  * Created by lau on 6/2/16.
  */

trait AmountOtherT extends EMVTLVLeaf {

  override val tag: BerTag = AmountOther.tag

}

case class AmountOther(override val value: Seq[Byte]) extends AmountOtherT {

  require(value.length == AmountOther.length)

}

trait AmountOtherSpec extends EMVDefaultNumericWithLengthSpec[AmountOther] {

  val length = 6

  val tag: BerTag = "9F03"

  override val max: Int = 12
  override val min: Int = 12
}

object AmountOther extends AmountOtherSpec


