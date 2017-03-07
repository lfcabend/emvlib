package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithVarLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._


/**
  * Created by lau on 11/7/16.
  */
case class ICCDynamicNumber(override val value: ByteVector) extends EMVTLVLeaf() {

  override val tag = ICCDynamicNumber.tag

}

object ICCDynamicNumber extends EMVDefaultBinaryWithVarLengthSpec[ICCDynamicNumber] {

  override val maxLength = 8

  override val minLength = 2

  val tag  = berTag"9F4C"


  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(ICCDynamicNumber, parseB(_))

}
