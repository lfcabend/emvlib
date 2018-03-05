package org.emv.tlv

import scodec.bits._
import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.joda.time.LocalTime
import org.lau.tlv.ber._

/**
  * Created by lau on 2/17/17.
  */
case class UnpredictableNumber(override val value: ByteVector) extends EMVTLVLeaf {

  override val tag = UnpredictableNumber.tag

}

object UnpredictableNumber extends
  EMVDefaultBinaryWithLengthSpec[UnpredictableNumber] {

  val tag = berTag"9F37"

  def parser = parseEMVBySpec(UnpredictableNumber, parseB(_))

  override val length = 4

}