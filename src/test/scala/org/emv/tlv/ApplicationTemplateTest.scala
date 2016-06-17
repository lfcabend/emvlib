package org.emv.tlv

import org.emv.tlv.EMVTLV.EMVParser
import org.scalatest.{FlatSpec, Matchers}
import org.tlv.HexUtils
import org.tlv.TLV.BerTag

/**
  * Created by lau on 6/8/16.
  */
class ApplicationTemplateTest extends FlatSpec with Matchers {

  "A Application Template" should " be able to parse" in {
    val input: Seq[Byte] = HexUtils.hex2Bytes("6106870101870101")
    EMVTLV.EMVParser.parse(EMVParser.parseApplicationTemplate, input) match {
      case EMVParser.Success(ac, _) => println(s"another ${ac}")
      case EMVTLV.EMVParser.Failure(msg, _) => println("FAILURE: " + msg)
      case EMVTLV.EMVParser.Error(msg, _) => println("ERROR: " + msg)
      case _ => println("failure")
    }

  }

  "A CDOL1" should " be to be constructed from a lis of tags and lengths" in {

    val cdolList : List[(BerTag, Int)] = (AmountAuthorized.tag, AmountAuthorized.length) ::
      (AmountOther.tag, AmountOther.length) ::Nil

    val cdol1: CardRiskManagementDataObjectList1 = CardRiskManagementDataObjectList1(cdolList)


    println(HexUtils.toHex(cdol1.value))
  }
}
