package org.emv.tlv


import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._
import scodec.bits.ByteVector

/**
  * Created by lau on 2/17/17.
  */
case class TransactionType(override val value: ByteVector) extends EMVTLVLeaf {

  override val tag = TransactionType.tag

}

object TransactionType extends
  EMVDefaultNumericWithLengthSpec[TransactionType] {

  val tag = berTag"9C"

  def parser = parseEMVBySpec(TransactionType, parseB(_))

  override val length = 1
  override val max = 2
  override val min = 2


}