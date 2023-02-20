package com.paolobroglio.loxinterpreter
package scanner.scanners

import org.scalatest.funsuite.AnyFunSuite

class NumberScannerTest extends AnyFunSuite {
  test("scan parses '10' number successfully") {
    val result = NumberScanner.scan("10", 1)
    assert(result.isRight)
    result.fold(
      _ => fail(),
      e => {
        assert(e.token.lexeme == "10")
        assert(e.token.literal == 10)
      }
    )
  }
  test("scan parses '10.1' number successfully") {
    val result = NumberScanner.scan("10.1", 1)
    assert(result.isRight)
    result.fold(
      _ => fail(),
      e => {
        assert(e.token.lexeme == "10.1")
        assert(e.token.literal == 10.1)
      }
    )
  }
}
