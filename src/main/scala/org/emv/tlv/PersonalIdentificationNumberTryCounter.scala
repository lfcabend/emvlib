package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._


/**
  * Created by lau on 11/28/16.
  */
case class PersonalIdentificationNumberTryCounter(override val value: ByteVector) extends EMVTLVLeaf {

  override val tag = PersonalIdentificationNumberTryCounter.tag

}

object PersonalIdentificationNumberTryCounter extends EMVDefaultBinaryWithLengthSpec[PersonalIdentificationNumberTryCounter] {

  val tag = berTag"9F17"

  override val length = 1

  def parser = parseEMVBySpec(PersonalIdentificationNumberTryCounter, parseB(_))


}