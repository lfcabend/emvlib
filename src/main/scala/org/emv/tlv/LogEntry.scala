package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/17/16.
  */
case class LogEntry(override val value: Seq[Byte]) extends EMVTLVLeaf {

  override val tag: BerTag = LogEntry.tag

}

object LogEntry extends EMVDefaultBinaryWithLengthSpec[LogEntry] {

  val tag: BerTag = "9F4D"

  val length: Int = 2

}
