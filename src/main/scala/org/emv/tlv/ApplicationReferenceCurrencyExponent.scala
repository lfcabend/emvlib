package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVTLVLeaf, LeafToStringHelper}
import org.tlv.TLV.{BerTag, BerTLVLeafT}

/**
  * Created by lau on 6/6/16.
  */
case class ApplicationReferenceCurrencyExponent(override val value: Seq[Byte])
  extends EMVTLVLeaf {

  override val tag: BerTag = ApplicationReferenceCurrencyExponent.tag

}


object ApplicationReferenceCurrencyExponent {

  val lengthMax = 1

  val lengthMin = 4

  val tag: BerTag = "9F43"

  val min = 1

  val max = 1


}
