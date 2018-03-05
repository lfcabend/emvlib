package org.emv

import org.iso7816.APDU.{APDUCommand, APDUCommandResponse}

/**
  * Created by lau on 2/15/17.
  */
trait UserInterface {

  def reportPPSESelected(transmission: SelectTransmission, newState: TerminalState)

  def reportFinalAppSelected(transmission: SelectTransmission, newState: TerminalState)

  def reportGPOProcessed(transmission: GPOTransmission, newState: TerminalState)

  def reportReadRecordsProcessed(transmission: List[ReadRecordTransmission], newState: TerminalState)

  def cardConnected(connectionContext: ConnectionContext, terminalState: TerminalState): Unit

  def terminalInitialized(connectionContext: ConnectionContext, terminalState: TerminalState): Unit

  def isTransactionCanceled: Boolean

}
