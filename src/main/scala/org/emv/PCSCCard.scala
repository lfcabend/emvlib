package org.emv

import javax.smartcardio.{CardException, CardTerminal, CommandAPDU, ResponseAPDU, _}

import collection.JavaConverters._
import scalaz.concurrent.Task
import org.lau.tlv.ber._
import scodec.bits._
/**
  * Created by lau on 7/2/16.
  */
object PCSCCard extends CardTrait {

  val defaultProtocol: String = "*"

  override def initialize: Task[ConnectionContext] = Task{
    PCSCConnectionContext(None, None)
  }

  override def waitForCardOnTerminal(context: ConnectionContext): Task[ConnectionContext] =
    context match {
      case PCSCConnectionContext(c, r) =>
        for {
          reader <- selectReader(None)
          _ <- waitForCardOnTerminal(reader, 1000)
          card <- connectToCard(reader)
        } yield PCSCConnectionContext(Some(card), Some(reader))
      case _ => InvalidContextType(context)
    }

  override def transmit(context: ConnectionContext, commandBytes: ByteVector): Task[ByteVector] =
    context match {
      case PCSCConnectionContext(Some(c), Some(r)) => transmit(c, commandBytes)
      case _ => InvalidContextType(context)
    }

  override def close(context: ConnectionContext): Task[Unit] =
    context match {
      case PCSCConnectionContext(Some(c), Some(r)) => close(c)
      case _ => InvalidContextType(context)
    }

  def getReaders: Task[Seq[CardTerminal]] = Task {
    TerminalFactory.getDefault.terminals.list.asScala
  }

  def selectReader(preferedReader: Option[String]): Task[CardTerminal] =
    getReaders.map(readers => {
      val selectedPreferedReader: Option[CardTerminal] = preferedReader.flatMap(x => readers.find(y => y.getName == x))
      val selectedReader = selectedPreferedReader match {
        case Some(x) => Some(x)
        case None => readers.find(y => y.isCardPresent)
      }
      selectedReader match {
        case Some(x) => x
        case None if (readers.size > 0) => readers(0)
        case None => throw new ConnectToTargetError("No readers")
      }
    })

  def waitForCardOnTerminal(terminal: CardTerminal, timeout: Long): Task[Unit] = Task {
    terminal.waitForCardPresent(timeout)
  }.flatMap({
    case true => Task(Unit)
    case false => Task.fail(throw new TargetNotFoundError)
  })

  def connectToCard(terminal: CardTerminal, protocol: String = defaultProtocol): Task[javax.smartcardio.Card] = Task {
    terminal.connect(protocol)
  }

  def waitAndConnect(terminal: CardTerminal, timeout: Long,
                     protocol: String = defaultProtocol): Task[javax.smartcardio.Card] = for {
    _ <- waitForCardOnTerminal(terminal, timeout)
    card <- connectToCard(terminal, protocol)
  } yield (card)

  def transmit(card: javax.smartcardio.Card, commandBytes: ByteVector): Task[ByteVector] = Task {
    val channel: CardChannel = card.getBasicChannel
    val responseCommand = channel.transmit(new CommandAPDU(commandBytes.toArray))
    ByteVector(responseCommand.getBytes)
  }

  def close(card: javax.smartcardio.Card): Task[Unit] = Task {
    card.disconnect(true)
  }

}
