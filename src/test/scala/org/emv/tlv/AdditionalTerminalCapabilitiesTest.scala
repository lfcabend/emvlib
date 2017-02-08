package org.emv.tlv

import org.scalatest.{Matchers, FlatSpec}
import scala.util.parsing.combinator.Parsers
/**
  * Created by lau on 5/31/16.
  */
class AdditionalTerminalCapabilitiesTest extends FlatSpec with Matchers with Parsers {

//  "Additional Terminal Capabilities" should " have a cash variant" in {
//    val atc = AdditionalTerminalCapabilities(HexUtils.hex2Bytes("8000000000"))
//
//    assert(atc.isCash)
//
//  }
//
//  it should "also have a non cash variant" in {
//    val atc = AdditionalTerminalCapabilities(HexUtils.hex2Bytes("0000000000"))
//
//    assert(!atc.isCash)
//
//  }
//
//  it should " be possible tpo set unset cash" in {
//    val atc = AdditionalTerminalCapabilities()
//
//    assert(!atc.isCash)
//
//    val atc1 = atc.withCashSet
//
//    assert(atc1.isCash)
//
//    val atc2 = atc1.withCashUnSet
//
//    assert(!atc2.isCash)
//
//
//  }
//
//  it should "be possible to call toString" in {
//    val atc = AdditionalTerminalCapabilities()
//    println(atc.withGoodsSet.withCashSet)
//  }


}
