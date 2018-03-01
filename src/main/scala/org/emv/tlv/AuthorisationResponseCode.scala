package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultAlphaNumericWithLengthSpec, EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf, EMVTLVLeafTextable}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/16/16.
  */
case class AuthorisationResponseCode(override val value:ByteVector)
  extends EMVTLVLeaf with EMVTLVLeafTextable {

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

  def apply(s: String) = new AuthorisationResponseCode(TextHelper.textToBytes(s))

  val SUCCESSFUL = AuthorisationResponseCode("00")


}