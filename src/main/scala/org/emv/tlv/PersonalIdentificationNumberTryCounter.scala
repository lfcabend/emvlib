package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/28/16.
  */
case class PersonalIdentificationNumberTryCounter(override val value: Seq[Byte]) extends EMVTLVLeaf {

  override val tag: BerTag = PersonalIdentificationNumberTryCounter.tag

}

object PersonalIdentificationNumberTryCounter extends EMVDefaultBinaryWithLengthSpec[PersonalIdentificationNumberTryCounter] {

  val tag: BerTag = "9F17"

  override val length: Int = 1

}