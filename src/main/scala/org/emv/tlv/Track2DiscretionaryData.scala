package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._
import scodec.bits.ByteVector

case class Track2DiscretionaryData(override val value: ByteVector)
  extends EMVTLVLeafTextable with TemplateTag {

  override val tag: BerTag = Track2DiscretionaryData.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)

}

object Track2DiscretionaryData extends EMVDefaultCompactNumericWithVarLengthSpec[Track2DiscretionaryData] {

  val tag = berTag"9F20"

  val max = 255

  val min = 0

  override val maxLength = 255

  override val minLength = 0

  def parser = parseEMVBySpec(Track2DiscretionaryData,
    parseANS(Track2DiscretionaryData)(_))
}



