package org.emv

import scalaz.concurrent.Task

/**
  * Created by lau on 2/15/17.
  */
trait Authorizer {

  def authorize(terminalState: TerminalState): Task[TerminalState]

}
