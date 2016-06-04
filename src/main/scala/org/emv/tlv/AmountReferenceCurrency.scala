package org.emv.tlv

import org.tlv.TLV.{BerTag, BerTLVLeafT}

/**
  * Created by lau on 6/2/16.
  */
case class AmountReferenceCurrency(override val value: Seq[Byte])
  extends BerTLVLeafT with EMVTLV.LeafToStringHelper {

  override def tag(): BerTag = AmountReferenceCurrency.tag

}

object AmountReferenceCurrency {

  val tag: BerTag = "9F3A"

  val length: Int = 4

}