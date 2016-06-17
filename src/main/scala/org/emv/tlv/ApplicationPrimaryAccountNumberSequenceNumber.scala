package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultNumericWithLengthSpec, EMVNumericWithLengthSpec, EMVTLVLeafNTextable}
import org.tlv.HexUtils
import org.tlv.TLV.{BerTag, BerTLVLeafT}

/**
  * Created by lau on 6/6/16.
  */
case class ApplicationPrimaryAccountNumberSequenceNumber(override val value: Seq[Byte])
  extends EMVTLVLeafNTextable {

  override val tag: BerTag = ApplicationPrimaryAccountNumberSequenceNumber.tag

}

object ApplicationPrimaryAccountNumberSequenceNumber extends EMVDefaultNumericWithLengthSpec[ApplicationPrimaryAccountNumberSequenceNumber] {

  val tag: BerTag = "5F34"

  val length: Int = 1

  override val max: Int = 2

  override val min: Int = 2

}
