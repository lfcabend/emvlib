package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by Lau on 5/22/2016.
  */
case class ApplicationPrimaryAccountNumber(override val value: ByteVector)
  extends EMVTLVLeafCNTextable with TemplateTag {

  override val tag = ApplicationPrimaryAccountNumber.tag

  override val templates = Set(ResponseMessageTemplateFormat2.tag,
    READRECORDResponseMessageTemplate.tag)
}

object ApplicationPrimaryAccountNumber extends EMVDefaultCompactNumericWithVarLengthSpec[ApplicationPrimaryAccountNumber] {

  val tag = berTag"5A"

  val length = 10

  val max = 19

  val min = 10

  override val maxLength = 10

  override val minLength = 1

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(ApplicationPrimaryAccountNumber, parseN(ApplicationPrimaryAccountNumber)(_))

}
