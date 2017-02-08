package org.emv.tlv

import fastparse.byte.all._
import org.emv.tlv.EMVTLV.EMVTLVParser._
import org.emv.tlv.EMVTLV._
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 11/28/16.
  */

trait ResponseMessageTemplateFormat1T

case class ResponseMessageTemplateFormat1(override val value: ByteVector) extends EMVTLVLeaf
  with ResponseMessageTemplateFormat1T {

  override val tag: BerTag = ResponseMessageTemplateFormat1.tag

}

case class GPOResponseMessageTemplateFormat1(v: (ApplicationInterchangeProfile,
  ApplicationFileLocator))
  extends EMVTLVLeaf with ResponseMessageTemplateFormat1T {

  val aip = v._1

  val afl = v._2

  override val tag: BerTag = GPOResponseMessageTemplateFormat1.tag

  override val value: ByteVector = v._1.value ++ v._2.value

}

object ResponseMessageTemplateFormat1 extends EMVBinaryWithVarLengthSpec[ByteVector, ResponseMessageTemplateFormat1] {

  val tag: BerTag = berTag"80"

  override val valueDataType: ValueDataType.Value = ValueDataType.B
  override val maxLength: Int = 255
  override val minLength: Int = 0

  def parser: Parser[ResponseMessageTemplateFormat1] =
    parseEMVBySpec(ResponseMessageTemplateFormat1, parseB(_))

}

object GPOResponseMessageTemplateFormat1
  extends EMVBinaryWithVarLengthSpec[(ApplicationInterchangeProfile, ApplicationFileLocator),
    GPOResponseMessageTemplateFormat1] {

  val tag: BerTag = berTag"80"

  override val valueDataType: ValueDataType.Value = ValueDataType.B

  def apply(aid: ApplicationInterchangeProfile, afl: ApplicationFileLocator) =
    new GPOResponseMessageTemplateFormat1(aid, afl)

  override val maxLength: Int = 255
  override val minLength: Int = 0

  def parseGPOResponseMessageTemplateFormat1: Parser[GPOResponseMessageTemplateFormat1] =
    parseEMVBySpec(GPOResponseMessageTemplateFormat1, parseGPOResponseMessageTemplateFormat1Value(_))

  def parseGPOResponseMessageTemplateFormat1Value(length: Int): Parser[(ApplicationInterchangeProfile, ApplicationFileLocator)] =
    for (
      aipValue <- parseB(2);
      aflEntries <- ApplicationFileLocator.parseAFLEntries(length - 2)
    ) yield (ApplicationInterchangeProfile(aipValue), ApplicationFileLocator(aflEntries))
}

