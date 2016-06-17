package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 6/15/16.
  */
case class AuthorisationCode(override val value:Seq[Byte])
  extends EMVTLVLeaf {

  override val tag: BerTag = AuthorisationCode.tag

}

object AuthorisationCode extends EMVDefaultBinaryWithLengthSpec[AuthorisationCode] {

  val tag: BerTag = "89"

  val length: Int = 6

}