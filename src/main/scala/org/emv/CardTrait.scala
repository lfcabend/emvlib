package org.emv

import scalaz.concurrent.Task
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 12/3/16.
  */
trait CardTrait {

  def initialize: Task[ConnectionContext]

  def waitForCardOnTerminal(context: ConnectionContext): Task[ConnectionContext]

  def transmit(context: ConnectionContext, commandBytes: ByteVector): Task[ByteVector]

  def close(context: ConnectionContext): Task[Unit]

  def InvalidContextType(context: ConnectionContext): Task[Nothing] = {
    Task.fail(new ConnectToTargetError(s"Invalid context type ${context.getClass.getName}"))
  }


}