package org.emv.gui

/**
  * Created by lcabenda on 4/14/2017.
  */
import scalafx.scene.control.TextField
import scalafx.scene.control.Button
import scalafx.scene.control.ListView
import scalafx.event.ActionEvent
import scalafxml.core.macros.sfxml

@sfxml
class TerminalController {

  // event handlers are simple public methods:
  def onCreate(event: ActionEvent) {
    println("oncreate")
  }


  def ok(event: ActionEvent) {
    println("ok")

  }

  def cancel(event: ActionEvent) {
    println("cancel")
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
  }
}
