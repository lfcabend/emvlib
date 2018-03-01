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
import org.emv.tlv.AmountAuthorized

@sfxml
class TerminalController(output: javafx.scene.text.Text) {

  val amount: AmountModel = new AmountModel()

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
    val amountAuthorized = amount.getAmountAuthorized()
    val terminalStateWithAmount = ApplicationContext.terminalState.withAmountAuthorized(amountAuthorized)
    val terminalState1 = TerminalProcessor.processTransaction(ApplicationContext.connectionConfig,
      ApplicationContext.card, terminalStateWithAmount)
    terminalState1(ApplicationContext.terminalEnv).unsafePerformSyncAttempt match {
      case \/-(x) => reportEndOfTransaction(x)
      case -\/(e) => reportError(e)
    }
  }

  def reportEndOfTransaction(transactionState: TerminalState)= {
    TerminalFX.notificationController.setText("Transaction Completed")
    TerminalFX.stage.scene_= (TerminalFX.notificationScene)
  }

  def reportError(e: Throwable): Unit = {
    e.printStackTrace()
    println(s"error: ${e.getMessage}")
    TerminalFX.notificationController.setText(e.getMessage)
    TerminalFX.stage.scene_= (TerminalFX.notificationScene)
  }

  def number(event: ActionEvent) {
    val button: javafx.scene.control.Button =
      event.getSource.asInstanceOf[javafx.scene.control.Button]

    println(s"number: ${button.getText}")
    val input = button.getText
    amount.addInput(input)
    output.setText(amount.getAmount())
  }

  def cancel(event: ActionEvent) {
    println("cancel")
    amount.reset()
    output.setText(amount.getAmount())
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
    amount.removeInput()
    output.setText(amount.getAmount())
  }

}
