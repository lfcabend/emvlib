package org.emv

import javax.smartcardio.Card

import org.iso7816.{AID, SelectResponse}

import scalaz.concurrent.Task

/**
  * Created by lau on 7/6/16.
  */
object TerminalProcessor {

  def performSelectPPSE(card: Card, terminalState: TerminalState): Task[TerminalState] =
    EMVCardHandler.performSelect(card, AID.PPSE).map(terminalState.withSelectPPSE(_))

  def performSelectApplication(card: Card, terminalState: TerminalState): Task[TerminalState] = {
    val ppseTrans = terminalState.transmissions.selectPPSETransmission
    ppseTrans match {
      case Some(SelectTransmission(Some(selectCommand), Some(SelectResponse(Some(fciTemplate), statusWord)))) => {
//        fciTemplate match {
////          case
//        }
        ???
      }
      case _ => Task.fail(new RuntimeException("did not perform ppse succesfully"))
    }
  }

}
