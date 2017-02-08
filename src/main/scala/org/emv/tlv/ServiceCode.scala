package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVDefaultNumericWithLengthSpec, EMVTLVLeafNTextable}
import org.lau.tlv.ber._
import scodec.bits._
/**
  * Created by lau on 11/28/16.
  */
case class ServiceCode(override val value: ByteVector)
  extends EMVTLVLeafNTextable {

  override val tag: BerTag = ServiceCode.tag

}

object ServiceCode extends EMVDefaultNumericWithLengthSpec[ServiceCode] {

  val tag: BerTag = berTag"5F30"

  val length: Int = 2

  override val max: Int = 3

  override val min: Int = 3

  def parser: Parser[ServiceCode] =
    parseEMVBySpec(ServiceCode, parseN(ServiceCode)(_))


}
