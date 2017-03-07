package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/7/16.
  */
case class IssuerActionCodeOnline(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = IssuerActionCodeOnline.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)

}

object IssuerActionCodeOnline extends EMVDefaultBinaryWithLengthSpec[IssuerActionCodeOnline] {

  val tag: BerTag = berTag"9F0F"

  override val length = 5

  def parser = parseEMVBySpec(IssuerActionCodeOnline, parseB(_))

}

