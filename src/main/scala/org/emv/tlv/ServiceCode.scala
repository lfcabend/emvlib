package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._
/**
  * Created by lau on 11/28/16.
  */
case class ServiceCode(override val value: ByteVector)
  extends EMVTLVLeafNTextable with TemplateTag {

  override val tag = ServiceCode.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)
}

object ServiceCode extends EMVDefaultNumericWithLengthSpec[ServiceCode] {

  val tag = berTag"5F30"

  val length  = 2

  override val max = 3

  override val min = 3

  def parser = parseEMVBySpec(ServiceCode, parseN(ServiceCode)(_))


}
