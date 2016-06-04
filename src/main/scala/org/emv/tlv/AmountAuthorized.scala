package org.emv.tlv

import org.emv.tlv.EMVTLV.{LeafToStringHelper, SingleTagParser}
import org.tlv.TLV.{BerTag, BerTLVLeafT}

/**
  * Created by lau on 6/1/16.
  */
case class AmountAuthorized (override val value: Seq[Byte])
  extends BerTLVLeafT with LeafToStringHelper {

  require(value.length == AmountAuthorized.length)

  override def tag(): BerTag = AccountType.tag

}

object AmountAuthorized {

  val length = 6

  val tag: BerTag = "9F02"
}

