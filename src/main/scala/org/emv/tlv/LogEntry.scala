package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/17/16.
  */
case class LogEntry(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag: BerTag = LogEntry.tag

  override val templates = Set(FileControlInformationIssuerDiscretionaryData.tag,
    DirectoryDiscretionaryTemplate.tag)
}

object LogEntry extends EMVDefaultBinaryWithLengthSpec[LogEntry] {

  val tag: BerTag = berTag"9F4D"

  val length: Int = 2

  def parser: Parser[LogEntry] =
    parseEMVBySpec(LogEntry, parseB(_))

}
