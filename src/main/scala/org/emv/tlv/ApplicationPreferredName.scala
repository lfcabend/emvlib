package org.emv.tlv

import java.nio.charset.StandardCharsets

import org.emv.tlv.EMVTLV.EMVTLVLeafTextable
import org.tlv.TLV.{BerTag, BerTLVLeafT}

/**
  * Created by lau on 6/5/16.
  */
case class ApplicationPreferredName(override val value: Seq[Byte]) extends EMVTLVLeafTextable {

  override val tag: BerTag = ApplicationPreferredName.tag

}

object ApplicationPreferredName {

  def apply(text: String) = new ApplicationPreferredName(text.getBytes(StandardCharsets.US_ASCII))

  val tag: BerTag = "9F12"

  val length: Int = 16

  val max: Int = 16

  val min: Int = 1

}

