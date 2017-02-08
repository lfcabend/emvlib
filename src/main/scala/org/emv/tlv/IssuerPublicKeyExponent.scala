package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithVarLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/10/16.
  */
case class IssuerPublicKeyExponent  (override val value: ByteVector)
  extends EMVTLVLeaf {

  override val tag: BerTag = IssuerPublicKeyExponent.tag

}

object IssuerPublicKeyExponent extends EMVDefaultBinaryWithVarLengthSpec[IssuerPublicKeyExponent] {

  val tag: BerTag = berTag"9F32"

  override val maxLength: Int = 3

  override val minLength: Int = 1

  def parser: Parser[IssuerPublicKeyExponent] =
    parseEMVBySpec(IssuerPublicKeyExponent, parseB(_))

}


