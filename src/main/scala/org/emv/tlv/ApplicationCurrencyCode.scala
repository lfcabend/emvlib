package org.emv.tlv

import java.util.Currency
import org.emv.tlv.EMVTLV.{LeafToStringHelper, SingleTagParser}
import org.tlv.TLV.{BerTag, BerTLVLeafT}

/**
  * Created by lau on 6/2/16.
  */
case class ApplicationCurrencyCode(override val value: Seq[Byte])
  extends BerTLVLeafT with LeafToStringHelper with CurrencyHelper {

  override def tag(): BerTag = ApplicationCryptogram.tag

}

object ApplicationCurrencyCode {

  val tag: BerTag = "9F42"

  val length: Int = 2

  val max: Int = 3

  val min: Int = 3

 }
