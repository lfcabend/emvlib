package org.emv.gui

import org.emv._
import org.iso7816.APDU.{APDUCommand, APDUCommandResponse}

/**
  * Created by Lau on 5/11/2017.
  */
class KernelController extends UserInterface {

  override def isTransactionCanceled: Boolean = false

  override def cardConnected(connectionContext: ConnectionContext, terminalState: TerminalState): Unit = {}

  override def terminalInitialized(connectionContext: ConnectionContext, terminalState: TerminalState): Unit = {}

  override def reportPPSESelected(transmission: SelectTransmission, newState: TerminalState): Unit = {}

  override def reportFinalAppSelected(transmission: SelectTransmission, newState: TerminalState): Unit = {}

  override def reportGPOProcessed(transmission: GPOTransmission, newState: TerminalState): Unit = {}

  override def reportReadRecordsProcessed(transmission: List[ReadRecordTransmission], newState: TerminalState): Unit = {}
}
