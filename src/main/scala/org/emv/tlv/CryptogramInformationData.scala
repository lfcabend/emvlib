package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVDefaultBinaryWithLengthSpec, EMVTLVLeaf}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/30/16.
  */
case class CryptogramInformationData(override val value: ByteVector) extends EMVTLVLeaf {

  override val tag: BerTag = CryptogramInformationData.tag

  override val postFixLabel = Some(cidType.toString)

  lazy val cidType: CIDType.Value = value(0) match {
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

  override val tag: BerTag = berTag"9F27"

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parseCryptogramInformationData: Parser[CryptogramInformationData] =
    parseEMVBySpec(CryptogramInformationData, parseB(_))

}