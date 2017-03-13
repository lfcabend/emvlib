package org.emv.tlv

import fastparse.core.Parsed
import org.scalatest.{FlatSpec, Ignore, Matchers}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 12/16/16.
  */
class FileControlInformationIssuerDiscretionaryDataTest extends FlatSpec with Matchers {

  "A FileControlInformationIssuerDiscretionaryData tag" should " be able to parse" in {
    //    val input: Seq[Byte] = HexUtils.hex2Bytes("6106870101870101")
    println(FileControlInformationIssuerDiscretionaryData.tag.toString())
    EMVTLV.EMVTLVParser.parseTag(FileControlInformationIssuerDiscretionaryData).parse(hex"BF0C") match {
      case Parsed.Success(ac, _) => println(s"another ${ac}")
      case Parsed.Failure(r1, r2, r3) => {
        println("FAILURE: " + r1)
        println("FAILURE: " + r2)
        println("FAILURE: " + r3)
      }
    }

  }

  "A FileControlInformationIssuerDiscretionaryData" should " be able to parse" in {
    FileControlInformationIssuerDiscretionaryData.parser(EMVTLV.EMVTLVParser.parseEMVTLV).
      parse(hex"BF0C1A61184F07A0000000031010500A56495341204445424954870101") match {
      case Parsed.Success(ac, _) => println(s"another ${ac}")
      case Parsed.Failure(r1, r2, r3) => {
        println("FAILURE: " + r1)
        println("FAILURE: " + r2)
        println("FAILURE: " + r3)
        fail("didnt parse it")
      }
    }


  }

  "A FileControlInformationProprietaryTemplate" should " be able to parse" in {
    FileControlInformationProprietaryTemplate.parser(EMVTLV.EMVTLVParser.parseEMVTLV).
      parse(hex"A51DBF0C1A61184F07A0000000031010500A56495341204445424954870101") match {
      case Parsed.Success(ac, _) => println(s"another ${ac}")
      case Parsed.Failure(r1, r2, r3) => {
        println("FAILURE: " + r1)
        println("FAILURE: " + r2)
        println("FAILURE: " + r3)
        fail("didnt parse it")
      }
    }


  }


  "A FileControlInformationTemplate" should " be able to parse" in {

    FileControlInformationTemplate.parser(EMVTLV.EMVTLVParser.parseEMVTLV).
      parse(hex"6F2F840E325041592E5359532E4444463031A51DBF0C1A61184F07A0000000031010500A56495341204445424954870101") match {
      case Parsed.Success(ac, _) => println(s"another ${ac}")
      case Parsed.Failure(r1, r2, r3) => {
        println("FAILURE: " + r1)
        println("FAILURE: " + r2)
        println("FAILURE: " + r3)
        fail("didnt parse it")
      }
    }

  }

  "A DedicatedFileName" should " be able to parse" in {

    //    FileControlInformationTemplate.parser.parse(hex"6F2F840E325041592E5359532E4444463031A51DBF0C1A61184F07A0000000031010500A56495341204445424954870101") match {
    //    FileControlInformationTemplate.parser.parse(hex"6F10840E325041592E5359532E4444463031") match {
    DedicatedFileName.parser.parse(hex"840E325041592E5359532E4444463031") match {
      case Parsed.Success(ac, _) => println(s"another ${ac}")
      case Parsed.Failure(r1, r2, r3) => {
        println("FAILURE: " + r1)
        println("FAILURE: " + r2)
        println("FAILURE: " + r3)
        fail("didnt parse it")
      }
    }

  }

}
