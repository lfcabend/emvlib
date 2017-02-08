package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultAlphaNumericWithLengthSpec, EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/16/16.
  */
case class AuthorisationResponseCode(override val value:ByteVector)
  extends EMVTLVLeaf {

  override val tag: BerTag = AuthorisationCode.tag

}

object AuthorisationResponseCode extends EMVDefaultAlphaNumericWithLengthSpec[AuthorisationResponseCode] {

  val tag: BerTag = berTag"8A"

  val length: Int = 2

  val max = 2

  val min = 2

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[AuthorisationResponseCode] =
    parseEMVBySpec(AuthorisationResponseCode, parseAN(AuthorisationResponseCode)(_))


}