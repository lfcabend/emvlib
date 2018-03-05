package org.emv.tlv

import org.scalatest.{FlatSpec, Matchers}

import scalaz.{\/-, -\/}

/**
  * Created by lau on 6/16/16.
  */
class CDOL1Test extends FlatSpec with Matchers {

//  "A CDOL1" should " be to be constructed from a lis of tags and lengths" in {
//
//    val cdolList : List[(BerTag, Int)] = (AmountAuthorized.tag, AmountAuthorized.length) ::
//      (AmountOther.tag, AmountOther.length) ::Nil
//
//    val cdol1: CardRiskManagementDataObjectList1 = CardRiskManagementDataObjectList1(cdolList)
//
//    println(cdol1)
//  }
//
//  it should " be possible to parse it" in {
//    val input = "8C069F02069F0306"
//    EMVTLV.parseEMVTLV(input) match {
//      case EMVParser.Success(ac, _) => println(s"another ${ac}")
//      case EMVParser.Failure(msg, _) => println("FAILURE: " + msg)
//      case EMVParser.Error(msg, _) => println("ERROR: " + msg)
//    }
//  }
//
//  it should " be possible to create the DOL value" in {
//    val amountAuth = AmountAuthorized("000000000020")
//    val amountOther = AmountOther("000000000010")
//    val listTLV = List(amountAuth, amountOther)
//    val tlvMap = Map((amountAuth.tag -> amountAuth), (amountOther.tag, amountOther))
//
//    val cdolList : List[(BerTag, Int)] = (AmountAuthorized.tag, AmountAuthorized.length) ::
//      (AmountOther.tag, AmountOther.length) ::Nil
//
//    val cdol1: CardRiskManagementDataObjectList1 = CardRiskManagementDataObjectList1(cdolList)
//
//    cdol1.createDOLValue(tlvMap) match {
//      case -\/(x) => println(x)
//      case \/-(x) => println(toHex(x))
//    }
//  }
}

