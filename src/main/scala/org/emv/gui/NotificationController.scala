package org.emv.gui

import scalafx.event.ActionEvent
import scalafxml.core.macros.sfxml

@sfxml
class NotificationController(val output: javafx.scene.control.TextArea) extends NotificationControllerI {

  def exit(event: ActionEvent) {
    println("exit")
    output.setText("")
    TerminalFX.stage.scene_=(TerminalFX.rootScene)
  }

  override def setText(text: String): Unit = {
    output.setText(text)
  }
}
