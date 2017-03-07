package org.emv.tlv

import fastparse.core.Parsed
import org.scalatest.{FlatSpec, Matchers}

import scala.util.parsing.combinator.Parsers
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 2/23/17.
  */
class LanguagePreferenceTest extends FlatSpec with Matchers with Parsers {

    "Language Preference" should " be able to parse" in {
      val input = hex"5F2D086265616173716176"
      LanguagePreference.parser.parse(input) match {
        case Parsed.Success(ac, _) => println(s"another ${ac}")
        case Parsed.Failure(msg, _, _) => println("FAILURE: " + msg)
      }
    }
}