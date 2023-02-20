package com.paolobroglio.loxinterpreter
package scanner.scanners

import scanner.scanners.Responses.ScanResponse
import scanner.token.{Token, TokenTypes}

object TokenScanner {

  def nextCharMatch(expected: Char, line: String): Boolean = {
    if (line.isEmpty) {
      return false
    }

    line.head == expected
  }

  def scan(basicTokenType: TokenTypes.TokenType, composedTokenType: TokenTypes.TokenType, expectedChar: Char, line: String, lineNumber: Int): ScanResponse = {
    if (nextCharMatch(expectedChar, line.tail)) {
      ScanResponse(Token(composedTokenType, s"${line.head}${line.tail.head}", null, lineNumber), line.tail.tail, lineNumber)
    } else {
      ScanResponse(Token(basicTokenType, line.head.toString, null, lineNumber), line.tail, lineNumber)
    }
  }
}
