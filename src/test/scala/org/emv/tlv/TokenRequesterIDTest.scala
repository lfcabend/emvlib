package org.emv.tlv

import fastparse.core.Parsed
import org.scalatest.{FlatSpec, Matchers}
import fastparse.core.Parsed
import org.scalatest.{FlatSpec, Ignore, Matchers}
import org.lau.tlv.ber._
import org.lau.visa.dataelements.TokenRequesterID
import scodec.bits._

class TokenRequesterIDTest extends FlatSpec with Matchers {

  "A TokenRequesterID tag" should " be able to parse" in {
    TokenRequesterID.parser.
      parse(hex"9F1906090807090800") match {
      case Parsed.Success(ac, _) => println(s"another ${ac}")
      case Parsed.Failure(r1, r2, r3) => {
        println("FAILURE: " + r1)
        println("FAILURE: " + r2)
        println("FAILURE: " + r3)
        fail("did not parse it")
      }
    }

  }

}
