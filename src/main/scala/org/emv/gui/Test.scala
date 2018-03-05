package org.emv.gui

import org.emv._
import org.iso7816.APDU.{APDUCommand, APDUCommandResponse}

import scala.swing._

/**
  * Created by lau on 2/15/17.
  */
class UI extends MainFrame with UserInterface {

  title = "GUI Program #1"
  preferredSize = new Dimension(320, 240)
  contents = new Label("Here is the contents!")

  def isTransactionCanceled: Boolean = false

  override def cardConnected(connectionContext: ConnectionContext, terminalState: TerminalState): Unit = {}

  override def terminalInitialized(connectionContext: ConnectionContext, terminalState: TerminalState): Unit = {}

  override def reportPPSESelected(transmission: SelectTransmission, newState: TerminalState): Unit = {}

  override def reportFinalAppSelected(transmission: SelectTransmission, newState: TerminalState): Unit = {}

  override def reportGPOProcessed(transmission: GPOTransmission, newState: TerminalState): Unit = {}

  override def reportReadRecordsProcessed(transmission: List[ReadRecordTransmission], newState: TerminalState): Unit = {}
}

object GuiProgramOne {
  def main(args: Array[String]) {
    val ui = new UI
    ui.visible = true
    println("End of main function")
  }
}