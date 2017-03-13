package org.emv.tlv

import java.util.Currency

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits.ByteVector



/**
  * Created by lau on 2/17/17.
  */
case class TransactionCurrencyCode(val currency: Currency) extends EMVTLVLeafWithCurrency {

  override val tag = TransactionCurrencyCode.tag

}

object TransactionCurrencyCode extends EMVCurrencySpec[TransactionCurrencyCode] {

  val tag = berTag"5F2A"

  import fastparse.byte.all.Parser
  import org.emv.tlv.EMVTLV.EMVTLVParser._


  def parser = parseEMVBySpec(TransactionCurrencyCode, parseCurrencyCode(_))

}


