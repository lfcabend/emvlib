package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultNumericWithLengthSpec, EMVTLVLeafNTextable}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 11/28/16.
  */
case class ServiceCode(override val value: Seq[Byte])
  extends EMVTLVLeafNTextable {

  override val tag: BerTag = ServiceCode.tag

}

object ServiceCode extends EMVDefaultNumericWithLengthSpec[ServiceCode] {

  val tag: BerTag = "5F30"

  val length: Int = 2

  override val max: Int = 3

  override val min: Int = 3

}
