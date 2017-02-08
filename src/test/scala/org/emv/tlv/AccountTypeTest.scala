package org.emv.tlv

import fastparse.core.Parsed
import org.scalatest.{FlatSpec, Matchers}

import org.lau.tlv.ber._
import scodec.bits._
/**
  * Created by lau on 5/29/16.
  */
class AccountTypeTest extends FlatSpec with Matchers {

  "A AccountType" should " be able to parse" in {
    val input = hex"5F570120"
    AccountType.parser.parse(input) match {
      case Parsed.Success(ac, _) => println(s"another ${ac}")
      case Parsed.Failure(msg, _, _) => println("FAILURE: " + msg)
    }
  }
}
