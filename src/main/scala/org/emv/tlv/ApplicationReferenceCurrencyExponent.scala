package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVDefaultBinaryWithVarLengthSpec, EMVTLVLeaf, LeafToStringHelper}
import org.lau.tlv.ber._
import scodec.bits._
/**
  * Created by lau on 6/6/16.
  */
case class ApplicationReferenceCurrencyExponent(override val value: ByteVector)
  extends EMVTLVLeaf {

  override val tag: BerTag = ApplicationReferenceCurrencyExponent.tag

}


object ApplicationReferenceCurrencyExponent extends EMVDefaultBinaryWithVarLengthSpec[ApplicationReferenceCurrencyExponent] {

  val tag: BerTag = berTag"9F43"

  val min = 1

  val max = 1

  override val maxLength: Int = 4

  override val minLength: Int = 1

  //todo parse four numbers
  def parser: Parser[ApplicationReferenceCurrencyExponent] =
    parseEMVBySpec(ApplicationReferenceCurrencyExponent, parseB(_))


}
