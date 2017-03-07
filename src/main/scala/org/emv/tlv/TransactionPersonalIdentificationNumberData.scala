package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._
import scodec.bits.ByteVector

/**
  * Created by lau on 2/17/17.
  */
case class TransactionPersonalIdentificationNumberData(override val value: ByteVector) extends EMVTLVLeaf {

  override val tag = TransactionPersonalIdentificationNumberData.tag

}

object TransactionPersonalIdentificationNumberData extends
  EMVDefaultBinaryWithVarLengthSpec[TransactionPersonalIdentificationNumberData] {

  val tag = berTag"99"


  override val maxLength = 255

  override val minLength = 0

  def parser = parseEMVBySpec(TransactionPersonalIdentificationNumberData,
    parseB(_))
}