package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._
import scodec.bits.ByteVector

/**
  * Created by lau on 2/17/17.
  */
case class TransactionStatusInformation(override val value: ByteVector) extends EMVTLVLeaf {

  override val tag: BerTag = TransactionStatusInformation.tag

}

object TransactionStatusInformation extends
  EMVDefaultBinaryWithLengthSpec[TransactionStatusInformation] {

  val tag: BerTag = berTag"9B"

  def parser = parseEMVBySpec(TransactionStatusInformation, parseB(_))

  override val length: Int = 2
}