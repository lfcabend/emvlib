package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithVarLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/10/16.
  */
case class IssuerPublicKeyRemainder (override val value: ByteVector)
  extends EMVTLVLeaf {

  override val tag: BerTag = IssuerPublicKeyExponent.tag

}

object IssuerPublicKeyRemainder extends EMVDefaultBinaryWithVarLengthSpec[IssuerPublicKeyRemainder] {

  val tag: BerTag = berTag"92"

  override val maxLength: Int = 255

  override val minLength: Int = 0

  def parser: Parser[IssuerPublicKeyRemainder] =
    parseEMVBySpec(IssuerPublicKeyRemainder, parseB(_))

}

