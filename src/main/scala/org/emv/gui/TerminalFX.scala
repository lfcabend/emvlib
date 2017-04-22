package org.emv.gui

import org.emv.TerminalProcessor

import scalafx.application.JFXApp
import scalafx.application.JFXApp
import scalafx.Includes._
import scalafx.scene.Scene
import scala.reflect.runtime.universe.typeOf
import scalafxml.core.{FXMLView, DependenciesByType}

/**
  * Created by lcabenda on 4/14/2017.
  */
object TerminalFX extends JFXApp {

  val root = FXMLView(getClass.getResource("TestGui.fxml"),
    new DependenciesByType(Map(
      typeOf[TestDependency] -> new TestDependency("hello world"))))

  val menu = FXMLView(getClass.getResource("menu.fxml"),
    new DependenciesByType(Map(
      typeOf[TestDependency] -> new TestDependency("hello world"))))

  val rootScene = new Scene(root)
  val menuScene = new Scene(menu)
  stage = new JFXApp.PrimaryStage() {
    title = "Hello world"
    scene = rootScene
  }
}

class TestDependency(s: String)