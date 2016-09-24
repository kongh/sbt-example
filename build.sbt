import _root_.play.sbt.PlayImport._
import _root_.play.sbt.PlayJava

name := "sbt-example"
organization := "org.scalamacros"
version := "2.0.0"


scalaVersion in ThisBuild := "2.11.8"
run <<= run in Compile in core

lazy val macros = (project in file("macros")).settings(
 libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value
)

lazy val core = (project in file("core")) dependsOn macros

lazy val web = (project in file("web")).enablePlugins(PlayJava).settings(
   //this is true will call a jpa bug
   PlayKeys.externalizeResources := false,
   libraryDependencies ++= Seq(
     // If you enable PlayEbean plugin you must remove these
     // JPA dependencies to avoid conflicts.
     javaJpa,
     "org.hibernate" % "hibernate-entitymanager" % "4.3.8.Final"
     )
) dependsOn core