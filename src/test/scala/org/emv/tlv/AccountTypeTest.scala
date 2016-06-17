package org.emv.tlv

import org.emv.tlv.EMVTLV.{EMVParser, EMVParsers}
import org.scalatest.{Matchers, FlatSpec}
import org.tlv.HexUtils

import scala.util.parsing.combinator.Parsers

/**
  * Created by lau on 5/29/16.
  */
class AccountTypeTest extends FlatSpec with Matchers with Parsers {

  "A AccountType" should " be able to parse" in {
    val input = "5F570120"
    EMVTLV.parseEMVTLV(input) match {
      case EMVParser.Success(ac, _) => println(s"another ${ac}")
      case EMVParser.Failure(msg, _) => println("FAILURE: " + msg)
      case EMVParser.Error(msg, _) => println("ERROR: " + msg)
    }
  }
}
