package org.emv.gui

import org.emv.TerminalProcessor

import scalafx.application.JFXApp
import scalafx.Includes._
import scalafx.scene.Scene
import scala.reflect.runtime.universe.typeOf
import scalafxml.core.{DependenciesByType, FXMLLoader, FXMLView}

/**
  * Created by lcabenda on 4/14/2017.
  */
object TerminalFX extends JFXApp {


  val rootLoader = new FXMLLoader(getClass.getResource("/TestGui.fxml"),
    new DependenciesByType(Map(
      typeOf[TestDependency] -> new TestDependency("Bugus"))))

  rootLoader.load()

  val menuLoader = new FXMLLoader(getClass.getResource("/menu.fxml"),
    new DependenciesByType(Map()))

  menuLoader.load()

  val notificationLoader = new FXMLLoader(
    getClass.getResource("/notification.fxml"),
    new DependenciesByType(Map(
      typeOf[TestDependency] -> new TestDependency("Bugus")))
  )

  notificationLoader.load()

  val notificationController = notificationLoader.getController[NotificationControllerI]()

  val rootScene = new Scene(rootLoader.getRoot[javafx.scene.Parent])
  val menuScene = new Scene(menuLoader.getRoot[javafx.scene.Parent])
  val notificationScene = new Scene(notificationLoader.getRoot[javafx.scene.Parent])

  stage = new JFXApp.PrimaryStage() {
    title = "RaspTerminal"
    scene = rootScene
  }

}

class TestDependency(s: String)