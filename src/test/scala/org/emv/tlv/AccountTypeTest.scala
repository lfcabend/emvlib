package org.emv.tlv

import org.emv.tlv.EMVTLV.EMVParsers
import org.scalatest.{Matchers, FlatSpec}

import scala.util.parsing.combinator.Parsers

/**
  * Created by lau on 5/29/16.
  */
class AccountTypeTest extends FlatSpec with Matchers with Parsers {

  "A AccountType" should " be able to parse" in {
    val input = "5F570100"
    new EMVParsers {
      parseEMVTLV(input) match {
        case Success(ac@UnSpecifiedAccountType(), _) => println(ac)
        case Success(ac, _) => println(s"another ${ac}")
        case Failure(msg, _) => println("FAILURE: " + msg)
        case Error(msg, _) => println("ERROR: " + msg)
      }

    }
    //    new AccountTypeParser() {
    //      parse(input) match {
    //        case Success(ac@UnSpecifiedAccountType(), _) => println(ac)
    //        case Success(ac, _) => println(s"another ${ac}")
    //        case Failure(msg, _) => println("FAILURE: " + msg)
    //        case Error(msg, _) => println("ERROR: " + msg)
    //      }
    //    }

  }

}
