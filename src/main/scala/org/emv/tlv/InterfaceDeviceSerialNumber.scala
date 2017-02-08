package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultAlphaNumericWithLengthSpec, EMVTLVLeafTextable}
import org.lau.tlv.ber._
import scodec.bits._


/**
  * Created by lau on 11/7/16.
  */
case class InterfaceDeviceSerialNumber(override val value: ByteVector) extends EMVTLVLeafTextable {

  override val tag: BerTag = InterfaceDeviceSerialNumber.tag

}

object InterfaceDeviceSerialNumber extends EMVDefaultAlphaNumericWithLengthSpec[InterfaceDeviceSerialNumber] {

  val tag: BerTag = berTag"9F1E"

  override val max: Int = 8

  override val min: Int = 8

  override val length: Int = 8

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[InterfaceDeviceSerialNumber] =
    parseEMVBySpec(InterfaceDeviceSerialNumber, parseB(_))


}