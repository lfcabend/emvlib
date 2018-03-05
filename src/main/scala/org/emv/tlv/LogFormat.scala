package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithVarLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._
/**
  * Created by lau on 11/17/16.
  */
case class LogFormat(override val value: ByteVector) extends EMVTLVLeaf {

  override val tag  = LogFormat.tag

}

object LogFormat extends EMVDefaultBinaryWithVarLengthSpec[LogFormat] {

  val tag = berTag"9F4F"

  override val maxLength = Int.MaxValue
  override val minLength = 0

  def parser = parseEMVBySpec(LogEntry, parseB(_))
}