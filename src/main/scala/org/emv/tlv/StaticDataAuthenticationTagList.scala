package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._
import scodec.bits.ByteVector

/**
  * Created by lau on 2/17/17.
  */

case class StaticDataAuthenticationTagList(tags: List[BerTag])
  extends EMVTLVLeaf with TemplateTag {

  override val tag = StaticDataAuthenticationTagList.tag

  override val value = tags.foldRight[ByteVector](ByteVector.empty)((x, y) => x.value ++ y)

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)

}

object StaticDataAuthenticationTagList extends EMVBinaryWithVarLengthSpec[List[BerTag], StaticDataAuthenticationTagList] {

  val tag = berTag"9F4A"

  override val maxLength = 255

  override val minLength = 0

  def parser = parseEMVBySpec(StaticDataAuthenticationTagList, parseTagList(_))

  def parseTagList(length: Int): Parser[List[BerTag]] =
    BerTLVParser.repParsingForXByte(BerTLVParser.parseTag, length)


}