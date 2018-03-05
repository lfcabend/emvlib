package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._
import scodec.bits.ByteVector

/**
  * Created by lau on 2/17/17.
  */
case class TransactionCurrencyExponent(override val value: ByteVector) extends EMVTLVLeafTextable {

  override val tag: BerTag = TransactionCurrencyExponent.tag

}

object TransactionCurrencyExponent extends EMVDefaultNumericWithLengthSpec[TransactionCurrencyExponent] {

  val tag: BerTag = berTag"5F36"

  def parser = parseEMVBySpec(TransactionCurrencyExponent,
    parseN(TransactionCurrencyExponent)(_))

  override val length: Int = 1

  override val max: Int = 1

  override val min: Int = 1

}