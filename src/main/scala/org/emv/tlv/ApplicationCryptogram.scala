package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/2/16.
  */
case class ApplicationCryptogram(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag: BerTag = ApplicationCryptogram.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag)

}

object ApplicationCryptogram extends EMVDefaultBinaryWithLengthSpec[ApplicationCryptogram] {

  val tag: BerTag = berTag"9F36"

  val length: Int = 8

  import fastparse.byte.all.Parser
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[ApplicationCryptogram] =
    parseEMVBySpec(ApplicationCryptogram, parseB(_))

}