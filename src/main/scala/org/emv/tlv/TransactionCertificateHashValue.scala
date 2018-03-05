package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._
import scodec.bits.ByteVector

/**
  * Created by lau on 2/17/17.
  */
case class TransactionCertificateHashValue(override val value: ByteVector) extends EMVTLVLeaf {

  override val tag = TransactionCertificateHashValue.tag

}

object TransactionCertificateHashValue extends EMVDefaultBinaryWithLengthSpec[TransactionCertificateHashValue] {

  val tag = berTag"98"

  def parser = parseEMVBySpec(TransactionCertificateHashValue, parseB(_))

  override val length = 20
}

