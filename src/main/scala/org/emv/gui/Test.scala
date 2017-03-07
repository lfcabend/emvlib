package org.emv.gui

import org.emv.UserInterface

import scala.swing._

/**
  * Created by lau on 2/15/17.
  */
class UI extends MainFrame with UserInterface {

  title = "GUI Program #1"
  preferredSize = new Dimension(320, 240)
  contents = new Label("Here is the contents!")

  def isTransactionCanceled: Boolean = false
}

object GuiProgramOne {
  def main(args: Array[String]) {
    val ui = new UI
    ui.visible = true
    println("End of main function")
  }
}