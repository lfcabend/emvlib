package org.emv.tlv

import org.emv.tlv.EMVTLV.{LeafToStringHelper, SingleTagParser}
import org.tlv.TLV.{BerTag, BerTLVLeafT}

/**
  * Created by lau on 6/2/16.
  */
case class AmountOther(override val value: Seq[Byte])
  extends BerTLVLeafT with LeafToStringHelper {

  require(value.length == AmountOther.length)

  override def tag(): BerTag = AmountOther.tag

}

object AmountOther {

  val length = 6

  val tag: BerTag = "9F03"

  //  trait AmountOtherParser extends SingleTagParser[AmountOther] {
  //
  //    def parseAmountOther = parse
  //
  //    override def parse: Parser[AmountOther] =
  //      parseLeafwithTag(tag, length, parseN, AmountOther(_))
  //
  //  }

}

