name := """emvlib"""

organization := "org.emv"

version := "1.0"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

libraryDependencies += "org.tlv" %% "tlvlib" % "1.1-SNAPSHOT"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.1.7" % "test"


fork in run := true