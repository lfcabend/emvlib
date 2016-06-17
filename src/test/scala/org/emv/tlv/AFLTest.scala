package org.emv.tlv

import org.emv.tlv.EMVTLV.EMVParsers
import org.scalatest.{Matchers, FlatSpec}

/**
  * Created by lau on 6/4/16.
  */
class AFLTest extends FlatSpec with Matchers {

  "AFL" should " be able to parse" in {
    val input = s"94080801010010010100"

    new EMVParsers {
      parseEMVTLV(input) match {
        case Success(ac, _) => println(s"another ${ac}")
        case Failure(msg, _) => println("FAILURE: " + msg)
        case Error(msg, _) => println("ERROR: " + msg)
      }

    }

    //    new AcquirerIdentifierParser() {
    //      parse(input) match {
    //        case Success(ac, _) => println(ac)
    //        case Failure(msg, _) => println("FAILURE: " + msg)
    //        case Error(msg, _) => println("ERROR: " + msg)
    //      }
    //    }

  }
}
