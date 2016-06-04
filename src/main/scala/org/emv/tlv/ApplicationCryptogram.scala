package org.emv.tlv

import org.tlv.TLV.{BerTag, BerTLVLeafT}

/**
  * Created by lau on 6/2/16.
  */
case class ApplicationCryptogram (override val value: Seq[Byte])
  extends BerTLVLeafT with EMVTLV.LeafToStringHelper {

  override def tag(): BerTag = ApplicationCryptogram.tag

}

object ApplicationCryptogram {

  val tag: BerTag = "9F36"

  val length: Int = 8

}