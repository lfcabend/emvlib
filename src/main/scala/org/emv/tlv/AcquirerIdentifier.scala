package org.emv.tlv

import org.emv.tlv.EMVTLV.{LeafToStringHelper, SingleTagParser}
import org.tlv.TLV.{BerTag, BerTLVLeafT}

/**
  * Created by lau on 5/29/16.
  */
case class AcquirerIdentifier(override val value: Seq[Byte])
  extends BerTLVLeafT with LeafToStringHelper {

  require(value.length == AcquirerIdentifier.length)

  override def tag(): BerTag = AccountType.tag

}

object AcquirerIdentifier {

  val length = 6

  val tag: BerTag = "9F01"

  val min = 6

  val max = 11

}
