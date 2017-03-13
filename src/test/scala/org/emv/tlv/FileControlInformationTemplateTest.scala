package org.emv.tlv

import fastparse.core.Parsed
import org.scalatest.{FlatSpec, Matchers}
import fastparse.core.Parsed
import org.scalatest.{FlatSpec, Ignore, Matchers}
import org.lau.tlv.ber._
import scodec.bits._
/**
  * Created by lau on 3/8/17.
  */
class FileControlInformationTemplateTest extends FlatSpec with Matchers {

  "A FileControlInformationTemplate tag" should " be able to parse ppse response" in {
    FileControlInformationTemplate.parser(EMVTLV.EMVTLVParser.parseEMVTLV).
      parse(hex"6F2D840E325041592E5359532E4444463031A51BBF0C1861164F07A0000000031010500B5649534120435245444954") match {
      case Parsed.Success(ac, _) => println(s"another ${ac}")
      case Parsed.Failure(r1, r2, r3) => {
        println("FAILURE: " + r1)
        println("FAILURE: " + r2)
        println("FAILURE: " + r3)
        fail()
      }
    }

  }

  it should " be able to parse visa select response 1" in {
    FileControlInformationTemplate.parser(EMVTLV.EMVTLVParser.parseEMVTLV).
      parse(hex"6F388407A0000000031010A52D500B56495341204352454449549F38189F66049F02069F03069F1A0295055F2A029A039C019F37045F2D02656E") match {
      case Parsed.Success(ac, _) => println(s"another ${ac}")
      case Parsed.Failure(r1, r2, r3) => {
        println("FAILURE: " + r1)
        println("FAILURE: " + r2)
        println("FAILURE: " + r3)
        fail()
      }
    }

  }

  it should " be able to parse visa select response 2" in {
    FileControlInformationTemplate.parser(org.lau.visa.Parser.parseEMVTLV).
      parse(hex"6F438407A0000000031010A538500B56495341204352454449549F38189F66049F02069F03069F1A0295055F2A029A039C019F37045F2D02656EBF0C089F5A053132333435") match {
//      parse(hex"6F3B8407A0000000031010A530500B56495341204352454449549F38189F66049F02069F03069F1A0295055F2A029A039C019F37045F2D02656EBF0C00") match {
      case Parsed.Success(ac, _) => println(s"another ${ac}")
      case Parsed.Failure(r1, r2, r3) => {
        println("FAILURE: " + r1)
        println("FAILURE: " + r2)
        println("FAILURE: " + r3)
        fail()
      }
    }

  }


}
