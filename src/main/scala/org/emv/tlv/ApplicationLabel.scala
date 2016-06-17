package org.emv.tlv

import java.nio.charset.StandardCharsets

import org.emv.tlv.EMVTLV.{EMVDefaultAlphaNumericSpecialWithLengthSpec, EMVAlphaNumericSpecialWithLengthSpec, EMVAlphaNumericSpecialSpec, EMVTLVLeafTextable}
import org.tlv.TLV.{BerTag, BerTLVLeafT}

/**
  * Created by lau on 6/5/16.
  */
case class ApplicationLabel(override val value: Seq[Byte]) extends EMVTLVLeafTextable {

  override val tag: BerTag = ApplicationLabel.tag

}

object ApplicationLabel extends EMVDefaultAlphaNumericSpecialWithLengthSpec[ApplicationLabel] {

  val tag: BerTag = "50"

  val length: Int = 16

  val max: Int = 16

  val min: Int = 1

}
