package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/7/16.
  */
case class IssuerApplicationData(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = IssuerApplicationData.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag)

}

object IssuerApplicationData extends EMVDefaultBinaryWithVarLengthSpec[IssuerApplicationData] {

  val tag = berTag"9F10"

  override val maxLength = 32

  override val minLength = 0

  def parser = parseEMVBySpec(IssuerApplicationData, parseB(_))


}
