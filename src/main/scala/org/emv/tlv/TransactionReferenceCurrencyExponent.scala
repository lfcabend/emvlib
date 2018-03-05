package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._
import scodec.bits.ByteVector

/**
  * Created by lau on 2/17/17.
  */
case class TransactionReferenceCurrencyExponent(override val value: ByteVector) extends EMVTLVLeaf {

  override val tag = TransactionReferenceCurrencyExponent.tag

}

object TransactionReferenceCurrencyExponent extends
  EMVDefaultNumericWithLengthSpec[TransactionReferenceCurrencyExponent] {

  val tag = berTag"9F3D"

  def parser = parseEMVBySpec(TransactionReferenceCurrencyExponent,
    parseN(TransactionReferenceCurrencyExponent)(_))

  override val length = 1

  override val max  = 1

  override val min = 1

}