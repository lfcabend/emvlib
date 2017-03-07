package org.emv.tlv

import fastparse.core.Parsed
import org.scalatest.{FlatSpec, Matchers}

import scala.util.parsing.combinator.Parsers
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 3/7/17.
  */
class READRecordtemplateTest extends FlatSpec with Matchers with Parsers {

  "Read record template" should " be able to parse" in {
    val input = hex"70159F0D0500000000009F0E0500000000005F20034C4155"
//    val input = hex"70055F20034C4155"
    READRECORDResponseMessageTemplate.parser.parse(input) match {
      case Parsed.Success(ac, _) => println(s"another ${ac}")
      case Parsed.Failure(msg, a1, a2) => {
        println("FAILURE: " + msg)
        fail(s"could not parse: ${msg}\n ${a1}\n ${a2}");
      }
    }
  }
}