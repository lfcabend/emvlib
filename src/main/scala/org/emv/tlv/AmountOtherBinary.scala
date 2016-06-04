package org.emv.tlv

import org.emv.tlv.EMVTLV.{LeafToStringHelper, SingleTagParser}
import org.tlv.TLV.{BerTag, BerTLVLeafT}

/**
  * Created by lau on 6/1/16.
  */
case class AmountOtherBinary(override val value: Seq[Byte])
  extends BerTLVLeafT with LeafToStringHelper {

  require(value.length == AmountOtherBinary.length)

  override def tag(): BerTag = AmountOtherBinary.tag

}

object AmountOtherBinary {

  val length = 4

  val tag: BerTag = "9F04"

}


