package org.emv.tlv

import fastparse.core.Parsed
import org.scalatest.{FlatSpec, Matchers}
import org.lau.tlv.ber._
import scodec.bits._
/**
  * Created by lau on 2/7/17.
  */
class GPOResponseTest  extends FlatSpec with Matchers {

  "GPOResponse " should " be able to parse" in {

    org.emv.commands.GPOResponse.gpoResponseParserFormat1.parse(hex"800E7D004001010048010301500103009000") match {
      case Parsed.Success(ac, _) => println(s"another ${ac}")
      case Parsed.Failure(r1, r2, r3) => {
        println("FAILURE: " + r1)
        println("FAILURE: " + r2)
        println("FAILURE: " + r3)
      }
    }

  }
}
