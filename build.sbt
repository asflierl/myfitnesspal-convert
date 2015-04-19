organization := "eu.flierl"
name := "myfitnesspal-convert"
version := "1.0"

scalaVersion := "2.11.6"
scalacOptions := Seq("-unchecked", "-deprecation", "-language:_", "-encoding", "UTF-8", "-target:jvm-1.8")

libraryDependencies += "org.scala-lang.modules" %% "scala-pickling" % "0.10.0"