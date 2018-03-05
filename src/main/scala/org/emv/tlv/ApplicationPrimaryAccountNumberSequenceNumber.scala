package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultNumericWithLengthSpec, EMVTLVLeafNTextable, TemplateTag}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/6/16.
  */
case class ApplicationPrimaryAccountNumberSequenceNumber(override val value: ByteVector)
  extends EMVTLVLeafNTextable with TemplateTag {

  override val tag = ApplicationPrimaryAccountNumberSequenceNumber.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)

}

object ApplicationPrimaryAccountNumberSequenceNumber extends EMVDefaultNumericWithLengthSpec[ApplicationPrimaryAccountNumberSequenceNumber] {

  val tag = berTag"5F34"

  val length = 1

  override val max = 2

  override val min = 2

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(ApplicationPrimaryAccountNumberSequenceNumber, parseCN(ApplicationPrimaryAccountNumberSequenceNumber)(_))

}
