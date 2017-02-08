package org.emv.tlv

import fastparse.core.Parsed
import org.scalatest.{FlatSpec, Matchers}
import org.lau.tlv.ber._
import scodec.bits._
/**
  * Created by lau on 6/8/16.
  */
class ApplicationTemplateTest extends FlatSpec with Matchers {

  "A Application Template" should " be able to parse" in {
    EMVTLV.EMVTLVParser.parseEMVTLV.parse(hex"61184F07A0000000031010500A56495341204445424954870101") match {
      case Parsed.Success(ac, _) => println(s"another ${ac}")
      case Parsed.Failure(r1, r2, r3) => println("FAILURE: " + r3)
    }

  }
//
//  "A CDOL1" should " be to be constructed from a lis of tags and lengths" in {
//
//    val cdolList : List[(BerTag, Int)] = (AmountAuthorized.tag, AmountAuthorized.length) ::
//      (AmountOther.tag, AmountOther.length) ::Nil
//
//    val cdol1: CardRiskManagementDataObjectList1 = CardRiskManagementDataObjectList1(cdolList)
//
//
//    println(HexUtils.toHex(cdol1.value))
//  }
}
