package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultCompactNumericWithVarLengthSpec, EMVTLVLeafCNTextable}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by Lau on 5/22/2016.
  */
case class ApplicationPrimaryAccountNumber(override val value: ByteVector)
  extends EMVTLVLeafCNTextable {

  override val tag: BerTag = ApplicationPrimaryAccountNumber.tag

}

object ApplicationPrimaryAccountNumber extends EMVDefaultCompactNumericWithVarLengthSpec[ApplicationPrimaryAccountNumber] {

  val tag: BerTag = berTag"5A"

  val length: Int = 10

  val max: Int = 19

  val min: Int = 10

  override val maxLength: Int = 10

  override val minLength: Int = 1

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[ApplicationPrimaryAccountNumber] =
    parseEMVBySpec(ApplicationPrimaryAccountNumber, parseN(ApplicationPrimaryAccountNumber)(_))

}
