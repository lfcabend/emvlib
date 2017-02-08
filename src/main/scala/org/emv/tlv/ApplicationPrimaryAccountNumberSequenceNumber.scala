package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultNumericWithLengthSpec, EMVTLVLeafNTextable}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/6/16.
  */
case class ApplicationPrimaryAccountNumberSequenceNumber(override val value: ByteVector)
  extends EMVTLVLeafNTextable {

  override val tag: BerTag = ApplicationPrimaryAccountNumberSequenceNumber.tag

}

object ApplicationPrimaryAccountNumberSequenceNumber extends EMVDefaultNumericWithLengthSpec[ApplicationPrimaryAccountNumberSequenceNumber] {

  val tag: BerTag = berTag"5F34"

  val length: Int = 1

  override val max: Int = 2

  override val min: Int = 2

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[ApplicationPrimaryAccountNumberSequenceNumber] =
    parseEMVBySpec(ApplicationPrimaryAccountNumberSequenceNumber, parseCN(ApplicationPrimaryAccountNumberSequenceNumber)(_))

}
