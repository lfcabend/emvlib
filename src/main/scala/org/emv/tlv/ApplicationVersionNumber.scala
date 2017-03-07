package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._
/**
  * Created by lau on 6/9/16.
  */
case class ApplicationVersionNumber(override val value:ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag: BerTag = ApplicationVersionNumber.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)

}

object ApplicationVersionNumber extends EMVDefaultBinaryWithLengthSpec[ApplicationVersionNumber] {

  val tag: BerTag = berTag"9F08"

  val length: Int = 2

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[ApplicationVersionNumber] =
    parseEMVBySpec(ApplicationVersionNumber, parseB(_))

}