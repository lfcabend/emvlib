package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/15/16.
  */
case class AuthorisationCode(override val value: ByteVector)
  extends EMVTLVLeaf {

  override val tag: BerTag = AuthorisationCode.tag

}

object AuthorisationCode extends EMVDefaultBinaryWithLengthSpec[AuthorisationCode] {

  val tag: BerTag = berTag"89"

  val length: Int = 6

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[AuthorisationCode] =
    parseEMVBySpec(AuthorisationCode, parseB(_))

}