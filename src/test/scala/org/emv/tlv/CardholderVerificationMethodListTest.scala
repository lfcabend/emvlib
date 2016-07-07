package org.emv.tlv

import org.emv.tlv.EMVTLV.EMVParser
import org.scalatest.{FlatSpec, Matchers}
import org.tlv.HexUtils

/**
  * Created by lau on 6/27/16.
  */
class CardholderVerificationMethodListTest  extends FlatSpec with Matchers {

  "A Cardholder verification method list" should " be able to parse" in {
    val input: Seq[Byte] = HexUtils.hex2Bytes("8E0A00000010000000204101")
    EMVTLV.EMVParser.parse(EMVParser.parseCardholderVerificationMethodsList, input) match {
      case EMVParser.Success(ac, _) => println(s"another ${ac}")
      case EMVTLV.EMVParser.Failure(msg, _) => println("FAILURE: " + msg)
      case EMVTLV.EMVParser.Error(msg, _) => println("ERROR: " + msg)
      case _ => println("failure")
    }

  }

}
