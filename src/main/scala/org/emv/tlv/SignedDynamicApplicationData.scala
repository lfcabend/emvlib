package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithVarLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/28/16.
  */
case class SignedDynamicApplicationData(override val value: ByteVector) extends EMVTLVLeaf {

  override val tag: BerTag = SignedDynamicApplicationData.tag

}

object SignedDynamicApplicationData extends EMVDefaultBinaryWithVarLengthSpec[SignedDynamicApplicationData] {

  val tag: BerTag = berTag"9F4B"

  override val maxLength: Int = 255

  override val minLength: Int = 0

  def parser: Parser[SignedDynamicApplicationData] =
    parseEMVBySpec(SignedDynamicApplicationData, parseB(_))

}