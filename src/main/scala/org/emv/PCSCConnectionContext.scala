package org.emv

import javax.smartcardio.CardTerminal

/**
  * Created by lau on 12/3/16.
  */
case class PCSCConnectionContext(card: javax.smartcardio.Card, terminal: CardTerminal) extends ConnectionContext