package com.paolobroglio.loxinterpreter
package scanner.scanners

import com.paolobroglio.loxinterpreter.scanner.token.TokenTypes
import org.scalatest.funsuite.AnyFunSuite

class IdentifierScannerTest extends AnyFunSuite {
  test("correctly scan ELSE identifiers") {
    val result = IdentifierScanner.scan("else", 1)
    result.fold(
      m => fail(),
      r => assert(r.token.`type` == TokenTypes.ELSE, r.token.lexeme == "else")
    )
  }
  test("correctly scan random aVariable") {
    val result = IdentifierScanner.scan("aVariable", 1)
    result.fold(
      m => fail(),
      r => assert(r.token.`type` == TokenTypes.IDENTIFIER, r.token.lexeme == "aVariable")
    )
  }
}
