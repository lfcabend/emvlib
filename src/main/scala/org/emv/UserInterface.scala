package org.emv

/**
  * Created by lau on 2/15/17.
  */
trait UserInterface {

  def reportCommandProcessed[A, B](transmission: Transmission[A, B], newState: TerminalState): Unit

  def cardConnected(connectionContext: ConnectionContext, terminalState: TerminalState): Unit

  def terminalInitialized(connectionContext: ConnectionContext, terminalState: TerminalState): Unit

  def isTransactionCanceled: Boolean

}
