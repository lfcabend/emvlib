package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/17/16.
  */
case class LowerConsecutiveOfflineLimit(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = LowerConsecutiveOfflineLimit.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)
}

object LowerConsecutiveOfflineLimit extends EMVDefaultBinaryWithLengthSpec[LowerConsecutiveOfflineLimit] {

  val tag  = berTag"9F14"

  override val length = 1

  def parser = parseEMVBySpec(LowerConsecutiveOfflineLimit, parseB(_))
}