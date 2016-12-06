import AssemblyKeys._

seq(assemblySettings: _*)

name := """emvlib"""

organization := "org.emv"

version := "1.0"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

resolvers += "Local Maven Repository" at "file:///" + Path.userHome + "/.m2/repository"

resolvers += "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/"

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4"

libraryDependencies += "org.tlv" %% "tlvlib" % "1.1-SNAPSHOT"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.1.7" % "test"

libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.12.0"

//libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.4"

libraryDependencies += "org.scalaz" %% "scalaz-concurrent" % "7.2.4"

libraryDependencies += "com.softwaremill.quicklens" %% "quicklens" % "1.4.7"

libraryDependencies += "com.neovisionaries" % "nv-i18n" % "1.2"

libraryDependencies += "nfclib" % "libnfc" % "1.7.1"


fork in run := true


mainClass in assembly := Some("TestMain")