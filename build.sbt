import com.typesafe.sbt.packager.docker.ExecCmd

name := """scala-play-api"""
organization := "com.example"

version := "1.0.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.2"

val playVersion = "2.8.0"

libraryDependencies += guice
libraryDependencies += "com.typesafe.play" %% "play-json" % playVersion % Provided
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

javaOptions in Universal ++= Seq(
  "-Dpidfile.path=/dev/null",
  "-J-XX:InitialRAMPercentage=50",
  "-J-XX:MaxRAMPercentage=75",
  "-Dcom.ibm.tools.attach.enable=no",
  "-J-Xss128k",
  "-J-XsharedClasses:name=scala-play-api,expire=10080", // Expire in a week if unused.
  "-J-Xscm64m",
  "-J-XX:+ClassRelationshipVerifier",
  "-J-Xcompressedrefs",
  "-J-XX:ActiveProcessorCount=2"
)

dockerBaseImage := "adoptopenjdk/openjdk11-openj9:debian-slim"
dockerExposedPorts := Seq(9000)
dockerCommands ++= Seq(
  ExecCmd("RUN",
    "chmod", "u+x",
    s"${(defaultLinuxInstallLocation in Docker).value}/bin/${executableScriptName.value}")
)