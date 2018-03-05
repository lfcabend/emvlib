package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._
import scodec.bits.ByteVector

/**
  * Created by lau on 2/17/17.
  */
case class TransactionSequenceCounter(override val value: ByteVector) extends EMVTLVLeaf {

  override val tag = TransactionSequenceCounter.tag

}

object TransactionSequenceCounter extends
  EMVDefaultNumericWithVarLengthSpec[TransactionSequenceCounter] {

  val tag = berTag"9F41"

  def parser = parseEMVBySpec(TransactionSequenceCounter,
    parseN(TransactionSequenceCounter)(_))

  override val max = 8

  override val min = 4

  override val maxLength = 4
  override val minLength = 2
}