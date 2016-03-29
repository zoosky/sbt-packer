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

package com.artooa.sbt.packer

import java.io.File

object Packer {
  def main(args: Array[String]): Unit = {
    println("Hello World!!")

    val parser = new scopt.OptionParser[Config]("java -jar sbt-packer") {
      head("packer", "0.0.1")

      opt[File]('n', "nodeconf") valueName ("<file>") action { (x, c) =>
        c.copy(nodeconf = x)
      } text ("Node configuration as input (default: ./node.conf)")

      opt[File]('t', "target") valueName ("<file>") action { (x, c) =>
        c.copy(target = x)
      } text ("Target output dir name (default: ./target).")

      opt[Unit]('v', "verbose") action { (x, c) =>
        c.copy(verbose = true)
      } text ("Enable verbose output.")
    }

    /*parser.parse(args, Config()) map { c =>
      //DO Configuration
      println(c)
    } */

    parser.parse(args, Config()) match {
      case Some(config) => println(config)
      // do stuff
      //check if nodeconfig exists. If not, write defaultNode.conf out.
      case None         =>
      // arguments are bad, error message will have been displayed
    }

  }

  case class Config(
    target: File = new File("./target"),
    nodeconf: File = new File("./node.conf"),
    verbose: Boolean = false
  )

}
