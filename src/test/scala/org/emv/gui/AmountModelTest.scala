package org.emv.gui

import org.emv.tlv.AmountAuthorized
import org.scalatest.{FlatSpec, Matchers}
import scodec.bits._

class AmountModelTest extends FlatSpec with Matchers {

    "An AmountModel" should " should be bale to give the EMV amount authorized value" in {
      val am = new AmountModel()
      am.addInput("1")
      am.addInput("0")
      am.addInput("0")
      val r = am.getAmountAuthorized()
      val expected = AmountAuthorized(hex"000000000100")
      r should be(expected)
    }
}

