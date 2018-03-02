name := """citrine_demo"""

version := "2.6.x"

scalaVersion := "2.12.4"

crossScalaVersions := Seq("2.11.12", "2.12.4")

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies += guice
libraryDependencies ++= Seq(
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
  "org.typelevel"  %% "squants"  % "1.3.0",
  "org.scala-lang" % "scala-reflect" % "2.11.7"
)

