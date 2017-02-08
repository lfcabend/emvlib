package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/2/16.
  */

trait AmountOtherT extends EMVTLVLeaf {

  override val tag: BerTag = AmountOther.tag

}

case class AmountOther(override val value: ByteVector) extends AmountOtherT {

  require(value.length == AmountOther.length)

}

trait AmountOtherSpec extends EMVDefaultNumericWithLengthSpec[AmountOther] {

  val length = 6

  val tag: BerTag = berTag"9F03"

  override val max: Int = 12
  override val min: Int = 12
}

object AmountOther extends AmountOtherSpec {

  import fastparse.byte.all.Parser
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser: Parser[AmountOther] =
    parseEMVBySpec(AmountOther, parseN(AmountOther)(_))

}


