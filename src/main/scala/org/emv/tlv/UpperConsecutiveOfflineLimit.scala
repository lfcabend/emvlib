package org.emv.tlv

import scodec.bits._
import org.emv.tlv.EMVTLV._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.lau.tlv.ber._

/**
  * Created by lau on 2/17/17.
  */
case class UpperConsecutiveOfflineLimit(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = UpperConsecutiveOfflineLimit.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)

}

object UpperConsecutiveOfflineLimit extends
  EMVDefaultBinaryWithLengthSpec[UpperConsecutiveOfflineLimit] {

  val tag = berTag"9F23"

  def parser = parseEMVBySpec(UpperConsecutiveOfflineLimit, parseB(_))

  override val length = 1

}