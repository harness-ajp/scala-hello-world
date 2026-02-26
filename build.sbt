import sbtassembly.AssemblyPlugin.autoImport._

ThisBuild / version := "1.0.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.3.1"
ThisBuild / organization := "com.example"

lazy val commonSettings = Seq(
  libraryDependencies += "com.lihaoyi" %% "cask" % "0.9.2",
  libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.4.14"
)

lazy val backend = (project in file("backend"))
  .settings(
    name := "scala-backend",
    commonSettings,
    libraryDependencies += "com.lihaoyi" %% "upickle" % "3.1.3",
    Compile / mainClass := Some("com.example.backend.Main"),
    assembly / mainClass := Some("com.example.backend.Main"),
    assembly / assemblyJarName := "backend-1.0.0-SNAPSHOT.jar",
    assembly / assemblyMergeStrategy := {
      case PathList("META-INF", xs @ _*) => 
        xs match {
          case "MANIFEST.MF" :: Nil => MergeStrategy.discard
          case "services" :: _      => MergeStrategy.concat
          case _                    => MergeStrategy.discard
        }
      case "reference.conf" => MergeStrategy.concat
      case "application.conf" => MergeStrategy.concat
      case x if x.endsWith(".proto") => MergeStrategy.first
      case _ => MergeStrategy.first
    }
  )

lazy val frontend = (project in file("frontend"))
  .settings(
    name := "scala-frontend",
    commonSettings,
    Compile / mainClass := Some("com.example.frontend.Server"),
    assembly / mainClass := Some("com.example.frontend.Server"),
    assembly / assemblyJarName := "frontend-1.0.0-SNAPSHOT.jar",
    assembly / assemblyMergeStrategy := {
      case PathList("META-INF", xs @ _*) => 
        xs match {
          case "MANIFEST.MF" :: Nil => MergeStrategy.discard
          case "services" :: _      => MergeStrategy.concat
          case _                    => MergeStrategy.discard
        }
      case "reference.conf" => MergeStrategy.concat
      case "application.conf" => MergeStrategy.concat
      case x if x.endsWith(".proto") => MergeStrategy.first
      case _ => MergeStrategy.first
    }
  )

lazy val root = (project in file("."))
  .aggregate(backend, frontend)
  .settings(
    name := "scala-hello-world",
    publish := {},
    publishLocal := {}
  )
