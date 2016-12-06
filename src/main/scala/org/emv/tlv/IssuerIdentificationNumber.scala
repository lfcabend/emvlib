package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultNumericWithLengthSpec, EMVTLVLeafNTextable}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/10/16.
  */
case class IssuerIdentificationNumber(override val value: Seq[Byte])
  extends EMVTLVLeafNTextable {

  override val tag: BerTag = IssuerIdentificationNumber.tag

}

object IssuerIdentificationNumber extends EMVDefaultNumericWithLengthSpec[IssuerIdentificationNumber] {

  val tag: BerTag = "42"

  val length: Int = 3

  override val max: Int = 6

  override val min: Int = 6

}
