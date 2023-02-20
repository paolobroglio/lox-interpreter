package com.paolobroglio.loxinterpreter

import scanner.Scanner

import scala.io.Source
import scala.io.StdIn.readLine

object LoxInterpreter {

  def run(line: String): Unit = {
    Scanner.scanTokens(line, 1)
      .foreach(token => println(token))
  }

  def runFile(fileName: String): Unit = {
    val bufferedSource = Source.fromFile(fileName)
    for (line <- bufferedSource.getLines) {
      run(line)
    }
    bufferedSource.close()
  }

  def runPrompt(): Unit = {
    var exit = false
    while (!exit) {
      print("> ")
      val input = readLine()
      if (input == null)
        exit = true

      run(input)
    }
  }


  def main(args: Array[String]): Unit = {
    if (args.length > 1) {
      println("Usage: lox [script]")
      System.exit(1)
    } else if (args.length == 1) {
      runFile(args(0))
    } else {
      runPrompt()
    }
  }
}
