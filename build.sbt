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

lazy val actors = (project in file("actors"))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      `akka-actor`
    )
  )

lazy val `services-api` = (project in file ("services-api"))
  .settings(commonSettings: _*)

lazy val `api-gateway` = (project in file("api-gateway"))
  .dependsOn(actors)
  .enablePlugins(PlayScala)
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      `akka-actor`
    )
  )


val runAll = inputKey[Unit]("Runs all subprojects")

runAll := {
  (run in Compile in `actors`).evaluated
}

fork in run := true