package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 6/30/16.
  */
case class CryptogramInformationData(override val value: Seq[Byte]) extends EMVTLVLeaf {

  override val tag: BerTag = CryptogramInformationData.tag

  override val postFixLabel = Some(cidType.toString)

  val cidType: CIDType.Value = value(0) match {
    case x if (~x & 0xC0) == 0x00 => CIDType.AAC
    case x if (x & 0x40) == 0x40 => CIDType.TC
    case x if (x & 0x80) == 0x80 => CIDType.ARQC
    case _ => CIDType.RFU
  }

}

object CIDType extends Enumeration {

  val AAC, TC, ARQC, RFU = Value

}

object CryptogramInformationData extends EMVDefaultBinaryWithLengthSpec[CryptogramInformationData] {

  override val length: Int = 1

  override val tag: BerTag = "9F27"

}