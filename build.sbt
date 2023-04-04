import Dependencies._

ThisBuild / scalaVersion := "2.13.10"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.duanyifu"
ThisBuild / organizationName := "solution"

val http4sVersion = "0.23.18"
lazy val root = (project in file("."))
  .settings(
    name := "adventofcode2022scala",
    libraryDependencies += munit % Test,
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-ember-client" % http4sVersion,
//      "org.http4s" %% "http4s-ember-server" % http4sVersion,
//      "org.http4s" %% "http4s-dsl" % http4sVersion,
    )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
