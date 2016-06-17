package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultAlphaNumericWithLengthSpec, EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 6/16/16.
  */
case class AuthorisationResponseCode(override val value:Seq[Byte])
  extends EMVTLVLeaf {

  override val tag: BerTag = AuthorisationCode.tag

}

object AuthorisationResponseCode extends EMVDefaultAlphaNumericWithLengthSpec[AuthorisationResponseCode] {

  val tag: BerTag = "8A"

  val length: Int = 2

  val max = 2

  val min = 2

}