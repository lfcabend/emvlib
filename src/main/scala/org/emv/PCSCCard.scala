package org.emv

import javax.smartcardio.{CardException, CardTerminal, CommandAPDU, ResponseAPDU, _}

import com.typesafe.scalalogging.LazyLogging

import collection.JavaConverters._
import scalaz.concurrent.Task
import org.lau.tlv.ber._
import scodec.bits._

/**
  * Created by lau on 7/2/16.
  */
object PCSCCard extends CardTrait with LazyLogging {

  val defaultProtocol: String = "*"

  override def initialize(config: ConnectionConfig): Task[ConnectionContext] = config match {
    case cc@(_: PCSCConnectionConfig) => Task {
      logger.debug("Initializing PCSCCard")
      PCSCConnectionContext(cc, None, None)
    }
    case _ => Task.fail(new RuntimeException("Invalid connection config"))
  }

  override def waitForCardOnTerminal(context: ConnectionContext): Task[ConnectionContext] =
    context match {
      case PCSCConnectionContext(cc, _, _) =>
        for {
          reader <- selectReader(Some(cc.preferredReader))
          _ <- waitForCardOnTerminal(reader, cc.waitForCard)
          card <- connectToCard(reader)
        } yield PCSCConnectionContext(cc, Some(card), Some(reader))
      case _ => InvalidContextType(context)
    }

  override def transmit(context: ConnectionContext, commandBytes: ByteVector): Task[ByteVector] =
    context match {
      case PCSCConnectionContext(_, Some(c), Some(_)) => transmit(c, commandBytes)
      case _ => InvalidContextType(context)
    }

  override def close(context: ConnectionContext): Task[Unit] =
    context match {
      case PCSCConnectionContext(_, Some(c), Some(_)) => close(c)
      case _ => InvalidContextType(context)
    }

  def getReaders: Task[Seq[CardTerminal]] = Task {
    val readers = TerminalFactory.getDefault.terminals.list.asScala
    logger.trace(s"readers: ${
      readers.map(x => x.getName).mkString(", ")
    }")
    readers
  }

  def selectReader(preferredReader: Option[String]): Task[CardTerminal] =
    getReaders.map(readers => {
      logger.trace(s"preferred reader: ${
        preferredReader
      }")
      val selectedPreferredReader: Option[CardTerminal] = preferredReader.flatMap(x => readers.find(y => {
        logger.trace(s"find preferred reader: ${
          y.getName
        } == ${
          x
        } ")
        y.getName == x
      }))
      logger.trace(s"selected preferred reader: ${
        selectedPreferredReader
      }")
      val selectedReader = selectedPreferredReader match {
        case Some(x) => Some(x)
        case None => {
          logger.debug(s"Looking for terminal with card present")
          readers.find(y => y.isCardPresent)
        }
      }
      val result = selectedReader match {
        case Some(x) => x
        case None if (readers.size > 0) => readers(0)
        case None => throw new ConnectToTargetError("No readers")
      }
      logger.debug(s"Selected reader: ${
        result
      }")
      result
    })

  def waitForCardOnTerminal(terminal: CardTerminal, timeout: Long): Task[Unit] = Task {
    logger.debug(s"waiting for card on ${
      terminal
    }")
    terminal.waitForCardPresent(timeout)
  }.flatMap({
    case true => Task(Unit)
    case false => Task.fail(throw new TargetNotFoundError)
  })

  def connectToCard(terminal: CardTerminal, protocol: String = defaultProtocol): Task[javax.smartcardio.Card] = Task {
    logger.debug(s"connecting to card on terminal ${
      terminal
    }")
    terminal.connect(protocol)
  }

  def waitAndConnect(terminal: CardTerminal, timeout: Long,
                     protocol: String = defaultProtocol): Task[javax.smartcardio.Card] = for {
    _ <- waitForCardOnTerminal(terminal, timeout)
    card <- connectToCard(terminal, protocol)
  } yield (card)

  def transmit(card: javax.smartcardio.Card, commandBytes: ByteVector): Task[ByteVector] = Task {
    val channel: CardChannel = card.getBasicChannel
    logger.debug(s">>> ${
      commandBytes.toHex
    }")
    val responseCommand = channel.transmit(new CommandAPDU(commandBytes.toArray))
    logger.debug(s"<<< ${
      ByteVector(responseCommand.getBytes()).toHex
    }")
    ByteVector(responseCommand.getBytes)
  }

  def close(card: javax.smartcardio.Card): Task[Unit] = Task {
    card.disconnect(true)
  }

}
