name := "microservices-template"

organization := "eggm0n"

version := "1.0"

val akkaV = "2.3.10"

val `akka-actor` = "com.typesafe.akka" %% "akka-actor" % akkaV

lazy val commonSettings = Seq(
  scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8"),
  scalaVersion := "2.11.7",
  resolvers ++= Seq(
    "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/")
)

lazy val `microservices-template` = project in file(".")

lazy val messages = (project in file("messages"))
  .settings(commonSettings: _*)

lazy val `example-service` = (project in file ("example-service"))
  .dependsOn(messages)
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      `akka-actor`
    )
  )

lazy val `api-gateway` = (project in file("api-gateway"))
  .dependsOn(`example-service`)
  .enablePlugins(PlayScala)
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      `akka-actor`
    )
  )

val runAll = inputKey[Unit]("Runs all subprojects")

runAll := {
  (run in Compile in `example-service`).evaluated
  (run in Compile in `api-gateway`).evaluated
}

fork in run := true