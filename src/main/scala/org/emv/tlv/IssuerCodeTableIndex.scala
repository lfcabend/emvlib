package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultNumericWithLengthSpec, EMVTLVLeafNTextable}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/7/16.
  */
case class IssuerCodeTableIndex (override val value: Seq[Byte])
  extends EMVTLVLeafNTextable with TextableNumber {

  override val tag: BerTag = IssuerCodeTableIndex.tag

}

object IssuerCodeTableIndex extends EMVDefaultNumericWithLengthSpec[IssuerCodeTableIndex] {

  val tag: BerTag = "9F11"

  val length: Int = 1

  override val max: Int = 2

  override val min: Int = 2

}

