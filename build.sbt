organization := "eu.flierl"
name := "myfitnesspal-convert"
version := "1.0"

scalaVersion := "2.12.9"
scalacOptions := Seq("-unchecked", "-language:_", "-deprecation", "-opt:l:inline", "-opt-inline-from:**",
  "-opt-warnings:_", "-encoding", "UTF-8", "-target:jvm-1.8")

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % "0.8.0")

fork := true
