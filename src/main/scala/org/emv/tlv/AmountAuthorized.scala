package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultNumericWithLengthSpec, EMVTLVLeaf, EMVTLVParser}
import org.lau.tlv.ber._
import scodec.bits.ByteVector

/**
  * Created by lau on 6/1/16.
  */


trait AmountAuthorizedT extends EMVTLVLeaf {

  override val tag: BerTag = AmountAuthorized.tag

}

case class AmountAuthorized(override val value: ByteVector) extends AmountAuthorizedT {

  require(value.length == AmountAuthorized.length)

}


trait AmountAuthorizedSpec extends EMVDefaultNumericWithLengthSpec[AmountAuthorized] {

  val length = 6

  val tag: BerTag = berTag"9F02"

  override val max: Int = 12

  override val min: Int = 12

}

object AmountAuthorized extends AmountAuthorizedSpec {

  import fastparse.byte.all.Parser
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[AmountAuthorized] =
    parseEMVBySpec(AmountAuthorized, parseN(AmountAuthorized)(_))

}

