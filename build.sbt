lazy val sbtPacker = project
  .copy(id = "sbt-packer")
  .in(file("."))
  .enablePlugins(AutomateHeaderPlugin, GitVersioning)

name := "sbt-packer"

libraryDependencies ++= Vector(
  Library.scalaCheck % "test"
)

initialCommands := """|import com.artooa.sbt.packer._
                      |""".stripMargin
