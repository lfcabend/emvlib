package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultCompactNumericWithVarLengthSpec, EMVNumericWithVarLengthSpec, EMVTLVLeafCNTextable, EMVParsers}
import org.tlv.HexUtils
import org.tlv.TLV._


/**
  * Created by Lau on 5/22/2016.
  */
case class ApplicationPrimaryAccountNumber(override val value: Seq[Byte])
  extends EMVTLVLeafCNTextable {

  override val tag: BerTag = ApplicationPrimaryAccountNumber.tag

}

object ApplicationPrimaryAccountNumber extends EMVDefaultCompactNumericWithVarLengthSpec[ApplicationPrimaryAccountNumber]{

  val tag: BerTag = "5A"

  val length: Int = 10

  val max: Int = 19

  val min: Int = 10

  override val maxLength: Int = 10

  override val minLength: Int = 1
}
