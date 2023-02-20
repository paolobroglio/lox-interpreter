package com.paolobroglio.loxinterpreter
package scanner

import scanner.message.Messages
import scanner.token.TokenTypes

import org.scalatest.funsuite.AnyFunSuite

class ScannerTest extends AnyFunSuite {
  test("scanToken parses left paren given line with cursor") {
    val result = Scanner.scanToken("(", 1)
    assert(result.isRight)
    result.fold (
      _ => fail(),
      e => assert(e.token.`type` == TokenTypes.LEFT_PAREN)
    )
  }
  test("scanToken parses right paren given line with cursor") {
    val result = Scanner.scanToken(")", 1)
    assert(result.isRight)
    result.fold (
      _ => fail(),
      v => assert(v.token.`type` == TokenTypes.RIGHT_PAREN)
    )
  }
  test("scanToken parses comma given line with cursor") {
    val result = Scanner.scanToken( ",", 1)
    assert(result.isRight)
    result.fold (
      _ => fail(),
      e => assert(e.token.`type` == TokenTypes.COMMA)
    )
  }
  test("scanToken parses newline") {
    val result = Scanner.scanToken( "\n", 1)
    assert(result.isLeft)
    result.fold (
      {
        case Messages.ScannerSkippedMessage(unicodeValue, lineNumber, remainingLine) => assert(unicodeValue == 10L)
        case _ => fail()
      },
      _ => fail()
    )
  }
  test("scanTokens parses a whole line of lexemes") {
    val result = Scanner.scanTokens("!=\n>=\n{\n}\n) \"some string with \n newline\" (", 1)
    result.foreach(token => println(token.`type`))
    assert(result.nonEmpty)
  }
}
