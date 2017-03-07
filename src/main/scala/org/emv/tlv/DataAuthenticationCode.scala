package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}

import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 9/1/16.
  */
case class DataAuthenticationCode(override val value: ByteVector) extends EMVTLVLeaf {

  override val tag = DataAuthenticationCode.tag

}

object DataAuthenticationCode extends EMVDefaultBinaryWithLengthSpec[DataAuthenticationCode] {

  override val length = 2

  override val tag = berTag"9F45"

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(DataAuthenticationCode, parseB(_))

}