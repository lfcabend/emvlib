name := """emvlib"""

organization := "org.emv"

version := "1.0"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4"

libraryDependencies += "org.tlv" %% "tlvlib" % "1.1-SNAPSHOT"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.1.7" % "test"

libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.12.0"

libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.4"

libraryDependencies += "org.scalaz" %% "scalaz-concurrent" % "7.2.4"

fork in run := true