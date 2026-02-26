ThisBuild / organization := "com.example"
ThisBuild / version      := "1.0.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.3.1"

lazy val commonSettings = Seq(
  libraryDependencies ++= Seq(
    "ch.qos.logback" % "logback-classic" % "1.4.14"
  )
)

lazy val backend = project
  .in(file("backend"))
  .settings(
    commonSettings,
    name := "backend",
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "cask"    % "0.9.2",
      "com.lihaoyi" %% "upickle" % "3.1.3"
    ),
    assembly / mainClass        := Some("com.example.backend.Main"),
    assembly / assemblyJarName  := "backend-1.0.0-SNAPSHOT.jar",
    assembly / assemblyMergeStrategy := {
      case PathList("META-INF", xs @ _*) => MergeStrategy.discard
      case x                              => MergeStrategy.first
    }
  )

lazy val frontend = project
  .in(file("frontend"))
  .settings(
    commonSettings,
    name := "frontend",
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "cask" % "0.9.2"
    ),
    assembly / mainClass        := Some("com.example.frontend.Server"),
    assembly / assemblyJarName  := "frontend-1.0.0-SNAPSHOT.jar",
    assembly / assemblyMergeStrategy := {
      case PathList("META-INF", xs @ _*) => MergeStrategy.discard
      case x                              => MergeStrategy.first
    }
  )
