name := "2021-march"

version := "0.1"

scalaVersion := "2.13.5"

lazy val libraries = List(
  "com.github.julien-truffaut" %% "monocle-core"  % "2.1.0",
  "com.github.julien-truffaut" %% "monocle-macro" % "2.1.0",
  "com.disneystreaming" %% "weaver-framework" % "0.5.1" % Test
)

lazy val lensception = (project in file(".")).settings(
  libraryDependencies ++= libraries,
  testFrameworks += new TestFramework("weaver.framework.TestFramework")
)