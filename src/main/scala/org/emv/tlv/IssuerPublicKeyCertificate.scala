package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithVarLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._


/**
  * Created by lau on 11/10/16.
  */
// todo should improve the certificate
case class IssuerPublicKeyCertificate(override val value: ByteVector)
  extends EMVTLVLeaf {

  override val tag: BerTag = IssuerPublicKeyCertificate.tag

}

object IssuerPublicKeyCertificate extends EMVDefaultBinaryWithVarLengthSpec[IssuerPublicKeyCertificate] {

  val tag: BerTag = berTag"90"

  //todo
  override val maxLength: Int = 255
  override val minLength: Int = 0

  def parser: Parser[IssuerPublicKeyCertificate] =
    parseEMVBySpec(IssuerPublicKeyCertificate, parseB(_))

}
