package org.emv.tlv

import org.scalatest.{Matchers, FlatSpec}
import org.tlv.TLV.BerTag

/**
  * Created by lau on 5/29/16.
  */
class AcquirerIdentifierTest extends FlatSpec with Matchers {

  "A Acquirer Identifier" should " be able to parse" in {
    val input = s"9F01020001"

//    new AcquirerIdentifierParser() {
//      parse(input) match {
//        case Success(ac, _) => println(ac)
//        case Failure(msg, _) => println("FAILURE: " + msg)
//        case Error(msg, _) => println("ERROR: " + msg)
//      }
//    }

  }

}
