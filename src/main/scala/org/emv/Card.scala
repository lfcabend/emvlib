package org.emv

import javax.smartcardio.{CardException, CardTerminal, CommandAPDU, ResponseAPDU, _}

import scala.collection.JavaConversions._
import collection.JavaConverters._
import scalaz.concurrent.Task

/**
  * Created by lau on 7/2/16.
  */
object Card {

  val defaultProtocol: String = "*"

  def getReaders: Task[Seq[CardTerminal]] = Task {
    TerminalFactory.getDefault.terminals.list.asScala
  }

  def selectReader(preferedReader: Option[String]): Task[Option[CardTerminal]] =
    getReaders.map(readers => {
      val selectedPreferedReader: Option[CardTerminal] = preferedReader.flatMap(x => readers.find(y => y.getName == x))
      val selectedReader = selectedPreferedReader match {
        case Some(x) => Some(x)
        case None => readers.find(y => y.isCardPresent)
      }
      selectedReader match {
        case Some(x) => Some(x)
        case None if (readers.size > 0) => Some(readers(0))
        case None => None
      }
    })

  def waitForCardOnTerminal(terminal: CardTerminal, timeout: Long): Task[Unit] = Task {
    terminal.waitForCardPresent(timeout)
  }

  def connectToCard(terminal: CardTerminal, protocol: String = defaultProtocol): Task[javax.smartcardio.Card] = Task {
    terminal.connect(protocol)
  }

  def waitAndConnect(terminal: CardTerminal, timeout: Long,
                     protocol: String = defaultProtocol): Task[javax.smartcardio.Card] = for {
    _ <- waitForCardOnTerminal(terminal, timeout)
    card <- connectToCard(terminal, protocol)
  } yield (card)

  def transmit(card: javax.smartcardio.Card, commandBytes: Seq[Byte]): Task[Seq[Byte]] = Task {
    val channel: CardChannel = card.getBasicChannel
    val responseCommand = channel.transmit(new CommandAPDU(commandBytes.toArray))
    responseCommand.getBytes.toList
  }

}
