package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}

import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 9/1/16.
  */
case class DataAuthenticationCode(override val value: ByteVector) extends EMVTLVLeaf {

  override val tag: BerTag = DataAuthenticationCode.tag

}

object DataAuthenticationCode extends EMVDefaultBinaryWithLengthSpec[DataAuthenticationCode] {

  override val length: Int = 2

  override val tag: BerTag = berTag"9F45"

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parseDataAuthenticationCode: Parser[DataAuthenticationCode] =
    parseEMVBySpec(DataAuthenticationCode, parseB(_))

}