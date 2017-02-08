package org.emv.tlv

import com.neovisionaries.i18n.CountryCode
import org.emv._
import org.emv.commands.{GPOCommand, GPOResponseFormat1, ReadRecordCommand, ReadRecordResponse}
import org.iso7816._
import org.scalatest.{FlatSpec, Matchers}

import scalaz.{-\/, \/-}
import scalaz.concurrent.Task
import org.scalamock.scalatest.MockFactory
import org.lau.tlv.ber._
import scodec.bits._
import org.emv.EMVCardHandler._

/**
  * Created by lau on 12/16/16.
  */
class EMVCardTest extends FlatSpec with Matchers with MockFactory {

  "EMV card processor" should " be able to process a select" in {

    val context: ConnectionContext = mock[ConnectionContext]
    val card: CardTrait = mock[CardTrait]
    val aid: AID = AID(hex"5041592e5359532e444446303100")

    (card.transmit _).expects(*, argThat[ByteVector](x => {
      x == hex"00A404000E5041592E5359532E444446303100"
    })).returns(Task(hex"6F2F840E325041592E5359532E4444463031A51DBF0C1A61184F07A0000000031010500A564953412044454249548701019000"))

    val selectTask: Task[SelectTransmission] = EMVCardHandler.performSelect(context, card, aid)

    val expectedValueSelectCommand = Select.selectDFFirstOccurenceWithFCIResponse(aid)
    val fci = FileControlInformationTemplate(List(
      DedicatedFileName(AID(hex"325041592E5359532E4444463031")),
      FileControlInformationProprietaryTemplate(List(
        FileControlInformationIssuerDiscretionaryData(List(
          ApplicationTemplate(List(
            ApplicationDedicatedFileName(AID(hex"A0000000031010")),
            ApplicationLabel(hex"56495341204445424954"),
            ApplicationPriorityIndicator(hex"01")
          ))
        ))
      ))))
    val expectedResponse = SelectResponse(Some(fci), NormalProcessingNoFurtherQualification)

    selectTask.unsafePerformSyncAttempt match {
      case \/-(SelectTransmission(Some(a), Some(b))) => {
        a should be(expectedValueSelectCommand)
        b should be(expectedResponse)
      }
      case -\/(e) => {
        fail(e.toString)
      }
      case _ => fail("result did not match expected value")
    }
  }


  "EMV card processor" should " be able to process a GPO Command with no PDOL" in {

    val context: ConnectionContext = mock[ConnectionContext]
    val card: CardTrait = mock[CardTrait]

    (card.transmit _).expects(*, argThat[ByteVector](_ == hex"80A8000002830000")).
      returns(Task(hex"800E7D004001010048010301500103009000"))

    val gpoTask: Task[GPOTransmission] = EMVCardHandler.performGPO(context, card, pdol = None, tlvList = Nil)

    val expectedGPOCommand = GPOCommand()
    val expectedResponse = GPOResponseFormat1(Some(
      GPOResponseMessageTemplateFormat1(ApplicationInterchangeProfile(hex"7d00"),
        ApplicationFileLocator(List(AFLEntry(0x40.toByte, 0x01.toByte, 0x01.toByte, 0x00.toByte),
          AFLEntry(0x48.toByte, 0x01.toByte, 0x03.toByte, 0x01.toByte),
          AFLEntry(0x50.toByte, 0x01.toByte, 0x03.toByte, 0x00.toByte)
        ))
      )),
      NormalProcessingNoFurtherQualification)

    gpoTask.unsafePerformSyncAttempt match {
      case \/-(GPOTransmission(Some(a), Some(b))) => {
        a should be(expectedGPOCommand)
        b should be(expectedResponse)
      }
      case -\/(e) => {
        fail(e.toString)
      }
      case _ => fail("result did not match expected value")
    }
  }


  it should " be able to process a GPO Command with a PDOL" in {

    val context: ConnectionContext = mock[ConnectionContext]
    val card: CardTrait = mock[CardTrait]

    val pdol = ProcessingOptionsDataObjectList((IssuerCountryCode.tag, 2) :: Nil)

    (card.transmit _).expects(*, argThat[ByteVector](_ == hex"80a80000048302052800")).
      returns(Task(hex"800E7D004001010048010301500103009000"))

    val tagList = List(IssuerCountryCode(CountryCode.NL))

    val gpoTask: Task[GPOTransmission] = performGPO(context, card, Some(pdol), tagList)

    val expectedGPOCommand = GPOCommand(pdol, tagList)
    val expectedResponse = GPOResponseFormat1(Some(
      GPOResponseMessageTemplateFormat1(ApplicationInterchangeProfile(hex"7d00"),
        ApplicationFileLocator(List(AFLEntry(0x40.toByte, 0x01.toByte, 0x01.toByte, 0x00.toByte),
          AFLEntry(0x48.toByte, 0x01.toByte, 0x03.toByte, 0x01.toByte),
          AFLEntry(0x50.toByte, 0x01.toByte, 0x03.toByte, 0x00.toByte)
        ))
      )),
      NormalProcessingNoFurtherQualification)

    gpoTask.unsafePerformSyncAttempt match {
      case \/-(GPOTransmission(Some(a), Some(b))) => {
        a should be(expectedGPOCommand)
        b should be(expectedResponse)
      }
      case -\/(e) => {
        fail(e.toString)
      }
      case _ => fail("result did not match expected value")
    }
  }


  it should "be able to process a Read Record Command" in {
    val context: ConnectionContext = mock[ConnectionContext]
    val card: CardTrait = mock[CardTrait]


    (card.transmit _).expects(*, argThat[ByteVector](_ == hex"00B2010C00")).
      returns(Task(hex"70009000"))

    val sfi = 0x01.toByte
    val record = 0x01.toByte

    val readRecordTrans: Task[ReadRecordTransmission] = readRecord(context, card, sfi, record)

    val expectedReadRecord = ReadRecordCommand(record, sfi)
    val expectedResponse = ReadRecordResponse(Some(READRECORDResponseMessageTemplate(Nil)),
      NormalProcessingNoFurtherQualification)

    readRecordTrans.unsafePerformSyncAttempt match {
      case \/-(ReadRecordTransmission(Some(a), Some(b))) => {
        a should be(expectedReadRecord)
        b should be(expectedResponse)
      }
      case -\/(e) => {
        fail(e.toString)
      }
      case _ => fail("result did not match expected value")
    }

  }


  it should "be able to process a Read Records Command" in {
    val context: ConnectionContext = mock[ConnectionContext]
    val card: CardTrait = mock[CardTrait]


    (card.transmit _).expects(*, argThat[ByteVector](_ == hex"00B2010C00")).
      returns(Task(hex"70009000"))
    (card.transmit _).expects(*, argThat[ByteVector](_ == hex"00B2020C00")).
      returns(Task(hex"70009000"))

    val sfi = 0x01.toByte
    val record = 0x01.toByte

    val afl = ApplicationFileLocator(List(AFLEntry(0x0C, 0x01, 0x02, 0x00)))

    val readRecordTrans: Task[Seq[ReadRecordTransmission]] = readRecords(context, card, afl)

    val expectedReadRecord = ReadRecordCommand(record, sfi)
    val expectedReadRecord2 = ReadRecordCommand((record + 1).toByte, sfi)
    val expectedResponse = ReadRecordResponse(Some(READRECORDResponseMessageTemplate(Nil)),
      NormalProcessingNoFurtherQualification)

    readRecordTrans.unsafePerformSyncAttempt match {
      case \/-(List(ReadRecordTransmission(Some(a), Some(b)),
          ReadRecordTransmission(Some(c), Some(d)))) => {
        a should be(expectedReadRecord)
        b should be(expectedResponse)
        c should be(expectedReadRecord2)
        d should be(expectedResponse)
      }
      case -\/(e) => {
        fail(e.toString)
      }
      case a@_ => fail(s"result did not match expected value: ${a}")
    }

  }

}
