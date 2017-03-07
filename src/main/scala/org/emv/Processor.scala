package org.emv

import scalaz.concurrent.Task

/**
  * Created by lau on 2/15/17.
  */
trait Processor {

  def process(userInterface: UserInterface, context: ConnectionContext,
              card: CardTrait, terminalState: TerminalState): Task[TerminalState]

}
