package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/28/16.
  */
case class SignedDynamicApplicationData(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = SignedDynamicApplicationData.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag)
}

object SignedDynamicApplicationData extends EMVDefaultBinaryWithVarLengthSpec[SignedDynamicApplicationData] {

  val tag = berTag"9F4B"

  override val maxLength = 255

  override val minLength = 0

  def parser = parseEMVBySpec(SignedDynamicApplicationData, parseB(_))

}