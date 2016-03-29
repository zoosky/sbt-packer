lazy val sbtPacker = project
  .copy(id = "sbt-packer")
  .in(file("."))
  .enablePlugins(AutomateHeaderPlugin, GitVersioning, SbtTwirl)

name := "sbt-packer"
version :="0.0.1"

libraryDependencies ++= Vector(
  Library.scalaCheck % "test"
)

initialCommands := """|import com.artooa.sbt.packer._
                      |""".stripMargin


TwirlKeys.templateImports += "org.example._"

//TwirlKeys.templateImports += "com.github.nscala_time.time.Imports._"
//twirlImports := Seq("org.example.util._", "com.mycompany.DbTools")
TwirlKeys.templateImports := Seq(
  "com.artooa._",
  "com.github.nscala_time.time.Imports._",
  "java.net.URL" )

// Automatically find def main(args:Array[String]) methods from classpath
packAutoSettings



artifact in (Compile, assembly) := {
  val art = (artifact in (Compile, assembly)).value
  art.copy(`classifier` = Some("assembly"))
}

addArtifact(artifact in (Compile, assembly), assembly)


// skip test in assembly
test in assembly := {}

outputPath in assembly := baseDirectory.value / "assembly" / (name.value + "-" + version.value + ".jar")

cleanFiles <+= baseDirectory { base => base / "assembly" }

// skip test in assembly
test in assembly := {}


libraryDependencies += "com.github.scopt" %% "scopt" % "3.4.0"

//resolvers += Resolver.sonatypeRepo("public")
