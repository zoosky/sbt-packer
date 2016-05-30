/*
 * Copyright 2016 Andreas Kapp
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sbt.sbtpacker

import collection.JavaConverters._

object Test {
  def main(args: Array[String]) = {
    println(ClassLoader.getSystemClassLoader)
    println(this.getClass.getClassLoader)
    println(classOf[Object].getClassLoader)
    //println(this.getClass.getClassLoader.findResources("packer"))
    class MyClass
    val cl = classOf[MyClass].getClassLoader
    val resName = classOf[MyClass].getName.replace(".", "/") + ".class"

    println(s"getResource: ${cl.getResource(resName)}")
    println(s"getResourceAsStream: ${cl.getResourceAsStream(resName)}")
    println(s"getResources: ${cl.getResources(resName).asScala.mkString}")
    println(s"""getResources packer: ${cl.getResources("packer").asScala.mkString}""")

    import java.io.InputStream
    val stream: InputStream = getClass.getResourceAsStream("/packer/afolder/Some-LICENSE")
    val lines = scala.io.Source.fromInputStream(stream).getLines
    println(s"lines: ${lines.mkString}")

    import scala.collection.JavaConversions._
    import java.io.File
    for (file <- new File(".").listFiles) {
      println(file.getAbsolutePath)
    }
    // println("chars " + chars)
    //println(getResourceAsCharStream(this.getClass, "LICENSE"))
    //println(wordlines)
  }

  //io.BufferedSource

  //val resource = "sbtpkr/ScalaByExample.pdf"
  //val chars = getResourceAsCharStream(this.getClass, resource).iterator

  private def getResourceAsCharStream(clazz: Class[_], resource: String): Stream[Char] = {
    val stream = clazz.getClassLoader() getResourceAsStream resource
    if (stream == null) Stream.empty
    else Stream continually stream.read() takeWhile (_ != -1) map (_.asInstanceOf[Char])
  }

  def wordlines = {
    val is = getClass.getClassLoader.getResourceAsStream("sbtpkr/afolder/Some-LICENSE")
    scala.io.Source.fromInputStream(is).mkString
  }

}

object Test2 {
  def main(args: Array[String]) = {
    println("hello")
  }
}