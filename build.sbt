name := "fprime-layout"
organization in ThisBuild := "gov.nasa.jpl"
scalaVersion in ThisBuild := "2.13.1"

lazy val settings = Seq(
  scalacOptions ++= Seq(
    "-deprecation",
    "-unchecked",
  ),
  libraryDependencies ++= dependencies, 
  testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-oNCXELOPQRM"),
)

lazy val dependencies = Seq(
  "com.github.scopt" %% "scopt" % "4.0.0-RC2",
  "org.scala-lang.modules" %% "scala-xml" % "2.0.0-M1",
  "org.scalatest" % "scalatest_2.13" % "3.1.0" % "test",
)

lazy val root = (project in file("."))
  .settings(settings)
  .aggregate(
    lib,
    fpl_convert_xml,
    fpl_layout,
    fpl_write_pic,
  )

lazy val lib = project
  .settings(settings)

lazy val fpl_convert_xml = (project in file("tools/fpl-convert-xml"))
  .settings(settings)
  .dependsOn(lib)
  .enablePlugins(AssemblyPlugin)

lazy val fpl_layout = (project in file("tools/fpl-layout"))
  .settings(settings)
  .dependsOn(lib)
  .enablePlugins(AssemblyPlugin)

lazy val fpl_write_pic = (project in file("tools/fpl-write-pic"))
  .settings(settings)
  .dependsOn(lib)
  .enablePlugins(AssemblyPlugin)

