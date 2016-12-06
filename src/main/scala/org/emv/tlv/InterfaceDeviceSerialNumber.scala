package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultAlphaNumericWithLengthSpec, EMVTLVLeafTextable}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/7/16.
  */
case class InterfaceDeviceSerialNumber(override val value: Seq[Byte]) extends EMVTLVLeafTextable {

  override val tag: BerTag = InterfaceDeviceSerialNumber.tag

}

object InterfaceDeviceSerialNumber extends EMVDefaultAlphaNumericWithLengthSpec[InterfaceDeviceSerialNumber] {

  val tag: BerTag = "9F1E"

  override val max: Int = 8

  override val min: Int = 8

  override val length: Int = 8

}