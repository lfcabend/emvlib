package org.emv.tlv

import fastparse.core.Parsed
import org.scalatest.{FlatSpec, Matchers}
import fastparse.core.Parsed
import org.scalatest.{FlatSpec, Ignore, Matchers}
import org.lau.tlv.ber._
import scodec.bits._


/**
  * Created by lau on 3/9/17.
  */
class ApplicationPrimaryAccountNumberSequenceNumberTest extends FlatSpec with Matchers {

  "A ApplicationPrimaryAccountNumberSequenceNumber tag" should " be able to parse ppse response" in {
    ApplicationPrimaryAccountNumberSequenceNumber.parser.
      parse(hex"5F340100") match {
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
