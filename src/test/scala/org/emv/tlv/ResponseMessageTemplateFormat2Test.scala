package org.emv.tlv


import fastparse.core.Parsed
import org.scalatest.{FlatSpec, Matchers}
import fastparse.core.Parsed
import org.scalatest.{FlatSpec, Ignore, Matchers}
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 3/8/17.
  */
class ResponseMessageTemplateFormat2Test extends FlatSpec with Matchers {

  "A ResponseMessageTemplateFormat2 tag" should " be able to parse ppse response" in {
    ResponseMessageTemplateFormat2.parser(org.lau.visa.Parser.parseEMVTLV).
//      parse(hex"77659F26086C7C0BF5DC7F9159940408030300820200409F360200019F6C0201809F2701809F6E04238C00009F10201F4301512000000000010203040403450100000000000000000000000000000057124761739001010010D201220112345123456F5F340100") match {
      parse(hex"77659F26086C7C0BF5DC7F9159940408030300820200409F360200019F6C0201809F2701809F6E04238C00009F10201F4301512000000000010203040403450100000000000000000000000000000057124761739001010010D201220112345123456F5F340100") match {
      case Parsed.Success(ac, _) => println(s"another ${ac}")
      case Parsed.Failure(r1, r2, r3) => {
        println("FAILURE77659F26086C7C0BF5DC7F9159940408030300820200409F360200019F6C0201809F2701809F6E04238C00009F10201F4301512000000000010203040403450100000000000000000000000000000057124761739001010010D201220112345123456F5F340100: " + r1)
        println("FAILURE: " + r2)
        println("FAILURE: " + r3)
        fail("did not parse it")
      }
    }

  }

}
