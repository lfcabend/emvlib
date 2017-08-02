package org.emv

import org.emv.TerminalProcessor.TransactionSt

import scalaz.concurrent.Task

/**
  * Created by lau on 2/15/17.
  */
trait Processor {

  def process(context: ConnectionContext, card: CardTrait, terminalState: TerminalState): TransactionSt

}
