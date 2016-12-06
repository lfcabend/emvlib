package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithVarLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/17/16.
  */
case class LogFormat(override val value: Seq[Byte]) extends EMVTLVLeaf {

  override val tag: BerTag = LogFormat.tag

}

object LogFormat extends EMVDefaultBinaryWithVarLengthSpec[LogFormat] {

  val tag: BerTag = "9F4F"

  override val maxLength: Int = Int.MaxValue
  override val minLength: Int = 0
}