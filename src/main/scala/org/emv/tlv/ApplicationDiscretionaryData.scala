package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._
import scodec.bits.ByteVector


case class ApplicationDiscretionaryData(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = ApplicationDiscretionaryData.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)
}

object ApplicationDiscretionaryData extends EMVDefaultBinaryWithVarLengthSpec[ApplicationDiscretionaryData] {

  val tag = berTag"9F05"

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(ApplicationDiscretionaryData, parseB(_))

  override val maxLength = 32
  override val minLength = 1
}