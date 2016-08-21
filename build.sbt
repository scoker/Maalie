name := "Maalie"

version := "1.0"

scalaVersion := "2.11.8"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.3.6"
  val sprayV = "1.3.2"
  val Json4sVersion = "3.2.11"

  Seq(
    "io.spray"            %%  "spray-can"     % sprayV withSources() withJavadoc(),
    "io.spray"            %%  "spray-routing" % sprayV withSources() withJavadoc(),
    "io.spray"            %%  "spray-json"    % "1.3.1",
    "io.spray"            %%  "spray-testkit" % sprayV  % "test",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",
    "org.scalaz"          %%  "scalaz-core"   % "7.1.0",
    "org.scalatest"       %%  "scalatest"     % "2.2.6" % "test",
    "org.json4s"          %%  "json4s-native" % Json4sVersion,
    "org.json4s"          %%  "json4s-ext"    % Json4sVersion
  )
}