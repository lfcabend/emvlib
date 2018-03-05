package org.emv

import javax.smartcardio.CardTerminal

/**
  * Created by lau on 12/3/16.
  */
case class PCSCConnectionContext(connectionConfig: PCSCConnectionConfig, card: Option[javax.smartcardio.Card], terminal: Option[CardTerminal])
  extends ConnectionContext