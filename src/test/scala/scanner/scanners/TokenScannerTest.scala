package com.paolobroglio.loxinterpreter
package scanner.scanners

import scanner.token.TokenTypes

import org.scalatest.funsuite.AnyFunSuite

class TokenScannerTest extends AnyFunSuite {
  test("nextCharMatch matches '=' sign operation") {
    val result = TokenScanner.nextCharMatch('=', "=")
    assert(result)
  }
  test("scanOperationToken parses '!=' expression correctly") {
    val result = TokenScanner.scan(TokenTypes.BANG, TokenTypes.BANG_EQUAL, '=', "!=", 1)
    assert(result.token.`type` == TokenTypes.BANG_EQUAL)
  }
}
