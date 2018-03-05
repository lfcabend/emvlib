package org.lau.visa

import org.emv._

import scalaz._
import Scalaz._
import scalaz.concurrent.Task
import org.emv.TerminalProcessor._

import scala.language.implicitConversions

/**
  * Created by lau on 2/10/17.
  */
object VisaTerminalProcessor extends Processor {

  override def process(context: ConnectionContext,
                       card: CardTrait,
                       terminalState: TerminalState) = for {

    ts3 <- processGetProcessingOptions(context, card, terminalState)

    _ <- failOnError(ts3, _.isPPSESuccessful, "GPO failed")

    _ <- checkIfUserCanceledTheTransaction

    ts4 <- processReadRecords(context, card, ts3)

    _ <- failOnError(ts3, _.isReadRecordsSuccessful, "Read records failed")

    _ <- checkIfUserCanceledTheTransaction

  } yield (ts4)

}

