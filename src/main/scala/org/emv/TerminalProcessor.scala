package org.emv

import javax.smartcardio.Card

import org.emv.tlv.EMVTLV.EMVParser
import org.iso7816.{AID, SelectResponse}

import scalaz.concurrent.Task

/**
  * Created by lau on 7/6/16.
  */
object TerminalProcessor {

  def performSelectPPSE(card: Card): Task[SelectResponse] =
    EMVCardHandler.performSelect(card, AID.PPSE)


}
