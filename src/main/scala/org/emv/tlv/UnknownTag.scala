package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultNumericWithLengthSpec, EMVTLVLeaf, EMVTLVType, LeafToStringHelper}
import org.lau.tlv.ber.{BerTLV, BerTLVLeaf, BerTLVParser, BerTag, PathX}
import scodec.bits.ByteVector


case class UnknownTag(val berTLV: BerTLVLeaf) extends EMVTLVLeaf {


  override lazy val label: String = s"${this.getClass.getSimpleName}(${tag.toString()})"

  override val tag: BerTag = berTLV.tag


  override val value: ByteVector = berTLV.value

}

object UnknownTag {

  import fastparse.byte.all._

  def parser= P(
    BerTLVParser.parseTLVLeaf.map(x => new UnknownTag(x))
  )

}