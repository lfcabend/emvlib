package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._
import scodec.bits.ByteVector

/**
  * Created by lau on 2/17/17.
  */
case class Track1DiscretionaryData(override val value: ByteVector)
  extends EMVTLVLeafTextable with TemplateTag {

  override val tag = Track1DiscretionaryData.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)

}

object Track1DiscretionaryData extends EMVDefaultAlphaNumericSpecialWithVarLengthSpec[Track1DiscretionaryData] {

  val tag = berTag"9F1F"

  val max = 255

  val min = 0

  override val maxLength = 255

  override val minLength = 0

  def parser = parseEMVBySpec(Track1DiscretionaryData,
    parseANS(Track1DiscretionaryData)(_))
}



