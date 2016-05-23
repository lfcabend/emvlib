package org.emv.tlv

import org.tlv.TLV.{BerTLV, BerTLVLeaf, BerTag, PathX, TLV}

/**
  * Created by Lau on 5/22/2016.
  */
case class PAN(override val value: Seq[Byte])
  extends BerTLVLeaf("57", value) {


}
