organization := "eu.flierl"
name := "myfitnesspal-convert"
version := "1.0"

scalaVersion := "2.11.6"
scalacOptions := Seq("-unchecked", "-language:_", "-encoding", "UTF-8", "-target:jvm-1.8")

libraryDependencies += "io.argonaut" %% "argonaut" % "6.1-M6"

fork := true
