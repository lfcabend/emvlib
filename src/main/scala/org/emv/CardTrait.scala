package org.emv

import scalaz.concurrent.Task

/**
  * Created by lau on 12/3/16.
  */
trait CardTrait {

  def waitForCardOnTerminal: Task[Option[ConnectionContext]]

  def transmit(context: ConnectionContext, commandBytes: Seq[Byte]): Task[Seq[Byte]]

  def close(context: ConnectionContext): Task[Unit]

}