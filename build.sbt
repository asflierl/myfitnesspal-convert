organization := "eu.flierl"
name := "myfitnesspal-convert"
version := "1.0"

ThisBuild / turbo := true

scalaVersion := "2.13.1"
scalacOptions := Seq("-unchecked", "-language:_", "-deprecation", "-opt:l:inline", "-opt-inline-from:**",
  "-encoding", "UTF-8", "-target:jvm-1.8")

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % "0.12.2")
