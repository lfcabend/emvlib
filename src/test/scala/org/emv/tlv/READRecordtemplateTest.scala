package org.emv.tlv

import fastparse.core.Parsed
import org.scalatest.{FlatSpec, Matchers}

import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 3/7/17.
  */
class READRecordtemplateTest extends FlatSpec with Matchers {

  "Read record template" should " be able to parse" in {
    val input = hex"702B5F200E4E616D6530696E205265636F72645F280208409F7C04010201029F070200809F1906090807090800"
//
//    val input = hex"70225F200E4E616D6530696E205265636F72645F280208409F7C04010201029F07020080"
//    val input = hex"70055F20034C4155"
    READRECORDResponseMessageTemplate.parser(org.lau.visa.Parser.parseEMVTLV).parse(input) match {
      case Parsed.Success(ac, _) => println(s"another ${ac}")
      case Parsed.Failure(msg, a1, a2) => {
        println("FAILURE: " + msg)
        fail(s"could not parse: ${msg}\n ${a1}\n ${a2}");
      }
    }
  }
}