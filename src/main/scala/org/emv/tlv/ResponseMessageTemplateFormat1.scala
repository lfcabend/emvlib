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

  override val tag = ResponseMessageTemplateFormat1.tag

}

case class GPOResponseMessageTemplateFormat1(v: (ApplicationInterchangeProfile,
  ApplicationFileLocator))
  extends EMVTLVLeaf with ResponseMessageTemplateFormat1T {

  val aip = v._1

  val afl = v._2

  override val tag = GPOResponseMessageTemplateFormat1.tag

  override val value = v._1.value ++ v._2.value

}

case class GenerateACResponseMessageTemplateFormat1(v:(CryptogramInformationData,
                                                    ApplicationTransactionCounter,
                                                    ApplicationCryptogram,
                                                    Option[IssuerApplicationData]))
  extends EMVTLVLeaf with ResponseMessageTemplateFormat1T {

  val cid = v._1

  val atc = v._2

  val ac = v._3

  val iad = v._4

  override val tag = GPOResponseMessageTemplateFormat1.tag

  override val value = v._1.value ++ v._2.value ++ v._3.value ++
    (if(v._4.isDefined) v._4.get.value else ByteVector.empty)

}



object ResponseMessageTemplateFormat1 extends EMVBinaryWithVarLengthSpec[ByteVector, ResponseMessageTemplateFormat1] {

  val tag = berTag"80"

  override val valueDataType = ValueDataType.B
  override val maxLength = 255
  override val minLength = 0

  def parser = parseEMVBySpec(ResponseMessageTemplateFormat1, parseB(_))

}

object GPOResponseMessageTemplateFormat1
  extends EMVBinaryWithVarLengthSpec[(ApplicationInterchangeProfile, ApplicationFileLocator),
    GPOResponseMessageTemplateFormat1] {

  val tag = berTag"80"

  override val valueDataType = ValueDataType.B

  def apply(aid: ApplicationInterchangeProfile, afl: ApplicationFileLocator) =
    new GPOResponseMessageTemplateFormat1(aid, afl)

  override val maxLength = 255
  override val minLength = 0

  def parseGPOResponseMessageTemplateFormat1 =
    parseEMVBySpec(GPOResponseMessageTemplateFormat1, parseGPOResponseMessageTemplateFormat1Value(_))

  def parseGPOResponseMessageTemplateFormat1Value(length: Int): Parser[(ApplicationInterchangeProfile, ApplicationFileLocator)] =
    for (
      aipValue <- parseB(2);
      aflEntries <- ApplicationFileLocator.parseAFLEntries(length - 2)
    ) yield (ApplicationInterchangeProfile(aipValue), ApplicationFileLocator(aflEntries))
}

object GenerateACResponseMessageTemplateFormat1
  extends EMVBinaryWithVarLengthSpec[(CryptogramInformationData,
    ApplicationTransactionCounter,
    ApplicationCryptogram,
    Option[IssuerApplicationData]),
    GenerateACResponseMessageTemplateFormat1] {

  val tag = berTag"80"

  override val valueDataType = ValueDataType.B

  override val maxLength = 255
  override val minLength = 0

  def parser = parseEMVBySpec(GenerateACResponseMessageTemplateFormat1, parseGenerateACResponseMessageTemplateFormat1Value)

  def parseGenerateACResponseMessageTemplateFormat1Value(length: Int): Parser[(CryptogramInformationData,
    ApplicationTransactionCounter,
    ApplicationCryptogram,
    Option[IssuerApplicationData])] =
    for (
      cidValue <- parseB(CryptogramInformationData.length);
      atcValue <- parseB(ApplicationTransactionCounter.length);
      acValue <- parseB(ApplicationCryptogram.length);
      iadValue <- parseB(length - CryptogramInformationData.length - ApplicationTransactionCounter.length - ApplicationCryptogram.length).?
    ) yield ((CryptogramInformationData(cidValue), ApplicationTransactionCounter(atcValue),
      ApplicationCryptogram(acValue), if (iadValue.isDefined) Some(IssuerApplicationData(iadValue.get)) else None))


}