import sbt._

object Version {
  final val Scala      = "2.11.8"
  final val ScalaCheck = "1.13.0"
  final val scopt      = "3.4.0"
}

object Library {
  val scalaCheck = "org.scalacheck" %% "scalacheck" % Version.ScalaCheck
  val scopt = "com.github.scopt" %% "scopt" % Version.scopt
}



//resolvers += Resolver.sonatypeRepo("public")
