package com.paolobroglio.loxinterpreter
package scanner.token

import org.scalatest.funsuite.AnyFunSuite

class TokenTest extends AnyFunSuite {
  test("get token if substring is valid") {
    val result = Token.fromTypeAndText(TokenTypes.AND, "var x = 1", 0, 3, 1)
    assert(result.isDefined)
    assert(result.get.lexeme == "var")
  }
  test("get none token if substring is invalid") {
    val result = Token.fromTypeAndText(TokenTypes.AND, "var x = 1", 0, 500, 1)
    assert(result.isEmpty)
  }

  test("get single char") {
    val result = Token.fromTypeAndText(TokenTypes.AND, "&", 0, 1, 1)
    assert(result.isDefined)
    assert(result.get.lexeme == "&")
  }
}
