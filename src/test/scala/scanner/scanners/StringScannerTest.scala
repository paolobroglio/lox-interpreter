package com.paolobroglio.loxinterpreter
package scanner.scanners

import scanner.message.Messages

import org.scalatest.funsuite.AnyFunSuite

class StringScannerTest extends AnyFunSuite {
  test("scanString parses '\"some string\"' string successfully") {
    val result = StringScanner.scan("some string\"", 1)
    assert(result.isRight)
    result.fold(
      _ => fail(),
      e => assert(e.token.lexeme == "some string")
    )
  }
  test("scanString parses '\"some\nstring\"' with newline successfully") {
    val result = StringScanner.scan("some\nstring\"", 1)
    assert(result.isRight)
    result.fold(
      _ => fail(),
      e => {
        assert(e.token.lexeme == "some\nstring")
        assert(e.lineNumber == 2)
      }
    )
  }
  test("scanString fails parsing unterminated string '\"some\nstring'") {
    val result = StringScanner.scan("some\nstring", 1)
    assert(result.isLeft)
    result.fold(
      {
        case Messages.ScannerError(message, lineNumber, remainingLine) =>
          assert(message.contains("Unterminated string"))
        case _ => fail()
      },
      e => fail()
    )
  }
}
