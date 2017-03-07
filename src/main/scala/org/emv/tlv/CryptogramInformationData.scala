package org.emv.tlv

import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 6/30/16.
  */
case class CryptogramInformationData(override val value: ByteVector)
  extends EMVTLVLeaf with TemplateTag {

  override val tag = CryptogramInformationData.tag

  override val postFixLabel = Some(cidType.toString)

  lazy val cidType: CIDType.Value = value(0) match {
    case x if (~x & 0xC0) == 0x00 => CIDType.AAC
    case x if (x & 0x40) == 0x40 => CIDType.TC
    case x if (x & 0x80) == 0x80 => CIDType.ARQC
    case _ => CIDType.RFU
  }

  lazy val adviceRequired: Boolean = (value(0).toByte & 0x08) == 0x08

  lazy val reasonAdviceCode: ReasonAdviceCode.Value = value(0) match {
    case x if (~x & 0x07) == 0x07 => ReasonAdviceCode.NoInformationGiven
    case x if (x & 0x01) == 0x01 => ReasonAdviceCode.ServiceNotAllowed
    case x if (x & 0x02) == 0x02 => ReasonAdviceCode.PINTryLimitExeceeded
    case x if (x & 0x03) == 0x03 => ReasonAdviceCode.IssuerAuthenticationFailed
    case _ => ReasonAdviceCode.RFU
  }

  override val templates = Set(ResponseMessageTemplateFormat2.tag)
}

object CIDType extends Enumeration {

  val AAC, TC, ARQC, RFU = Value

}

object ReasonAdviceCode extends Enumeration {

  val NoInformationGiven, ServiceNotAllowed, PINTryLimitExeceeded,
  IssuerAuthenticationFailed, RFU = Value

}

object CryptogramInformationData extends EMVDefaultBinaryWithLengthSpec[CryptogramInformationData] {

  override val length = 1

  override val tag = berTag"9F27"

  import fastparse.byte.all._
  import org.emv.tlv.EMVTLV.EMVTLVParser._

  def parser = parseEMVBySpec(CryptogramInformationData, parseB(_))

}