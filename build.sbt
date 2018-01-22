name := """play-heroku-seed"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  javaCore,
  javaEbean,
  javaWs,
  filters,
  "com.google.inject" % "guice" % "4.0-beta4",
  "com.h2database" % "h2" % "1.4.196",
  "org.postgresql" % "postgresql" % "9.3-1102-jdbc4"
)
