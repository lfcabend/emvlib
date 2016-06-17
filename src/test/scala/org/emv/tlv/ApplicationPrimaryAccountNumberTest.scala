package org.emv.tlv

import org.scalatest.{Matchers, FlatSpec}
import scala.util.parsing.combinator.Parsers

/**
  * Created by lau on 5/25/16.
  */
class ApplicationPrimaryAccountNumberTest extends FlatSpec with Matchers with Parsers {

//  "A PAN " should " be able to parse" in {
//    println("Test")
//    val input = s"5A09541245000000001FFF"
//
//    new PanParser() {
//      val pan: ParseResult[PAN] = parse(input)
//      pan match {
//        case Success(matched, _) => println(matched)
//        case Failure(msg, _) => println("FAILURE: " + msg)
//        case Error(msg, _) => println("ERROR: " + msg)
//      }
//    }
//
//    new ALLEMVParsers() {
//
//      val pan: ParseResult[BerTLV] = parse(input)
//      pan match {
//        case Success(matched, _) => println(matched)
//        case Failure(msg, _) => println("FAILURE: " + msg)
//        case Error(msg, _) => println("ERROR: " + msg)
//      }
//
//    }
//
//  }

}
