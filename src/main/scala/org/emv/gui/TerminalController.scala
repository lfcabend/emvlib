package org.emv.gui

/**
  * Created by lcabenda on 4/14/2017.
  */


import org.emv.TerminalProcessor

import scalafx.event.ActionEvent
import scalafx.scene.control.TextField
import scalafxml.core.macros.sfxml
import scalaz._
import org.emv.TerminalState

@sfxml
class TerminalController(output: javafx.scene.text.Text) {

  // event handlers are simple public methods:
  def onCreate(event: ActionEvent) {
    println("oncreate")
  }


  def ok(event: ActionEvent) {
    println("ok")
    //    TerminalFX.amount.getBinaryValue

    executeTransaction()
  }

  def executeTransaction(): Unit = {
    val terminalState1 = TerminalProcessor.processTransaction(ApplicationContext.connectionConfig,
      ApplicationContext.card, ApplicationContext.terminalState)
    terminalState1(ApplicationContext.terminalEnv).unsafePerformSyncAttempt match {
      case \/-(x) => reportEndOfTransaction(x)
      case -\/(e) =>
    }
  }

  def reportEndOfTransaction(transactionState: TerminalState)= {

  }

  def reportError(e: Exception) {

  }


  def number(event: ActionEvent) {
    val button: javafx.scene.control.Button =
      event.getSource.asInstanceOf[javafx.scene.control.Button]

    println(s"number: ${button.getText}")
    val input = button.getText
    TerminalFX.amount.addInput(input)
    output.setText(TerminalFX.amount.getAmount())
  }

  def cancel(event: ActionEvent) {
    println("cancel")
    TerminalFX.amount.reset()
    output.setText(TerminalFX.amount.getAmount())
  }

  def dot(event: ActionEvent) {
    println("dot")
  }

  def menu(event: ActionEvent) {
    println("menu")
    TerminalFX.stage.scene_=(TerminalFX.menuScene)
  }

  def backspace(event: ActionEvent) {
    println("backspace")
    TerminalFX.amount.removeInput()
    output.setText(TerminalFX.amount.getAmount())
  }

}
