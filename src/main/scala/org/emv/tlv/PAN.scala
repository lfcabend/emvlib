package org.emv.tlv

import org.emv.tlv.EMVTLV.{SingleTagParser, EMVParsers}
import org.tlv.TLV._


/**
  * Created by Lau on 5/22/2016.
  */
case class PAN(override val value: Seq[Byte])
  extends BerTLVLeafT with EMVTLV.LeafToStringHelper {

  override def tag(): BerTag = PAN.tag

}

object PAN {

  val tag: BerTag = "5A"

}
