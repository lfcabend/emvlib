package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._
import scodec.bits.ByteVector

/**
  * Created by lau on 2/17/17.
  */
case class Track2EquivalentData(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = Track2EquivalentData.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)

}

object Track2EquivalentData extends EMVDefaultBinaryWithVarLengthSpec[Track2EquivalentData] {

  val tag = berTag"57"

  def parser = parseEMVBySpec(Track2EquivalentData, parseB(_))

  override val maxLength = 19

  override val minLength = 0

}