package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._
/**
  * Created by lau on 11/7/16.
  */
case class IssuerAuthenticationData(override val value: ByteVector)
  extends EMVTLVLeaf {

  override val tag = IssuerAuthenticationData.tag

}

object IssuerAuthenticationData extends EMVDefaultBinaryWithVarLengthSpec[IssuerAuthenticationData] {

  val tag = berTag"91"

  override val maxLength = 16

  override val minLength = 8

  def parser = parseEMVBySpec(IssuerAuthenticationData, parseB(_))


}
