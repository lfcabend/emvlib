
name := """emvlib"""

organization := "org.emv"

version := "1.0"

scalaVersion := "2.12.4"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

//resolvers += "Local Maven Repository" at "file://d/Development/maven/repository/"

resolvers += "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/"

libraryDependencies += "org.tlv" %% "tlvlib" % "1.2-SNAPSHOT"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"

libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.16.0"

libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.11.8"

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.8"

libraryDependencies += "org.scalaz" %% "scalaz-concurrent" % "7.2.8"

libraryDependencies += "com.softwaremill.quicklens" %% "quicklens" % "1.4.11"

libraryDependencies += "com.neovisionaries" % "nv-i18n" % "1.2"

//libraryDependencies += "nfclib" % "libnfc" % "1.7.1"

libraryDependencies += "org.scala-lang" % "scala-swing" % "2.11.0-M7"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.7"

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0"

libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "3.5.0" % "test"

libraryDependencies += "org.scalafx" %% "scalafxml-core-sfx8" % "0.4"

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

fork in run := true

mainClass in assembly := Some("TestMain")
