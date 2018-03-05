package org.emv

import java.util.concurrent.ScheduledExecutorService

import scalaz.Reader

/**
  * Created by lcabenda on 3/31/2017.
  */
trait TerminalEnv {

  def authorizer: Authorizer

  def userInterface: UserInterface

  def executor: ScheduledExecutorService

}
