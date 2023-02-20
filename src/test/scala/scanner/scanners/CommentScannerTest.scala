package com.paolobroglio.loxinterpreter
package scanner.scanners

import com.paolobroglio.loxinterpreter.scanner.message.Messages
import com.paolobroglio.loxinterpreter.scanner.token.TokenTypes
import org.scalatest.funsuite.AnyFunSuite

class CommentScannerTest extends AnyFunSuite {
  test("scan parses '// some text' comment successfully") {
    val result = CommentScanner.scan("/ some text", 1)
    assert(result.isLeft)
    result.fold(
      {
        case Messages.ScannerSkippedComment(lineNumber, remainingLine) => assert(true)
        case _ => fail()
      },
      _ => fail()
    )
  }
  test("scan parses '// some text \n (' with newline successfully") {
    val result = CommentScanner.scan("/ some text", 1)
    assert(result.isLeft)
    result.fold(
      {
        case Messages.ScannerSkippedComment(lineNumber, remainingLine) => assert(true)
        case _ => fail()
      },
      _ => fail()
    )
  }
  test("scan parses '1' as a simple slash token") {
    val result = CommentScanner.scan("1", 1)
    assert(result.isRight)
    result.fold(
      _ => fail(),
      r => assert(r.token.`type` == TokenTypes.SLASH)
    )
  }
}
