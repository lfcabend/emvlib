package org.emv

import org.emv.tlv._
import org.scalatest.{FlatSpec, Matchers}
import scodec.bits._


class TerminalStateTest extends FlatSpec with Matchers {

  "Terminalstate " should " be bale to hold a amount authorized" in {
    val generalTags = GeneralParameters()

    val config = TerminalConfig(generalTags, List())

    val transientData = TerminalTransientData(Nil)

    val terminalState: TerminalState = TerminalState(config, transientData, TransactionTransmissions())

    val aa = AmountAuthorized(hex"000000001000")
    val updated = terminalState.withAmountAuthorized(aa)
    val r = updated.transientData.tlv.find(_.tag == AmountAuthorized.tag).get
    r should be(aa)
  }
}
