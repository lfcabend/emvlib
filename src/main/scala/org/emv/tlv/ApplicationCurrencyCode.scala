package org.emv.tlv

import java.util.Currency
import org.emv.tlv.EMVTLV._
import org.tlv.TLV.{BerTag, BerTLVLeafT}

/**
  * Created by lau on 6/2/16.
  */
case class ApplicationCurrencyCode(val currency: Currency) extends EMVTLVLeafWithCurrency {

  override val tag: BerTag = ApplicationCryptogram.tag

}

object ApplicationCurrencyCode extends EMVCurrencySpec[ApplicationCurrencyCode] {

  val tag: BerTag = "9F42"

 }
