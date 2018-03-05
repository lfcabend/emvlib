package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._


/**
  * Created by lau on 11/7/16.
  */
case class InterfaceDeviceSerialNumber(override val value: ByteVector) extends EMVTLVLeafTextable {

  override val tag = InterfaceDeviceSerialNumber.tag

}

object InterfaceDeviceSerialNumber extends EMVDefaultAlphaNumericWithLengthSpec[InterfaceDeviceSerialNumber] {

  val tag = berTag"9F1E"

  override val max = 8

  override val min = 8

  override val length = 8

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(InterfaceDeviceSerialNumber, parseB(_))


}