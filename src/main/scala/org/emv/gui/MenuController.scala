package org.emv.gui

import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafxml.core.macros.sfxml

/**
  * Created by lcabenda on 4/14/2017.
  */
@sfxml
class MenuController {


  def transactionType(event: ActionEvent) {
    println("transactionType")
  }

  def exit(event: ActionEvent) {
    println("exit")
    TerminalFX.stage.scene_=(TerminalFX.rootScene)
  }

}
