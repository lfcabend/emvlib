package org.emv.tlv

import java.util.Currency

import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._
import scodec.bits.ByteVector

/**
  * Created by lau on 2/17/17.
  */
case class TransactionReferenceCurrencyCode(val currency: Currency) extends EMVTLVLeafWithCurrency {

  override val tag = TransactionReferenceCurrencyCode.tag

}

object TransactionReferenceCurrencyCode extends EMVCurrencySpec[TransactionReferenceCurrencyCode] {

  val tag = berTag"9F3C"

  import fastparse.byte.all.Parser
  import org.emv.tlv.EMVTLV.EMVTLVParser._


  def parser = parseEMVBySpec(TransactionReferenceCurrencyCode, parseCurrencyCode(_))

}
