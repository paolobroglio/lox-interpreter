package com.paolobroglio.loxinterpreter
package scanner.scanners

import scanner.message.ScannerMessage
import scanner.scanners.Responses.ScanResponse

import com.paolobroglio.loxinterpreter.scanner.message.Messages.ScannerError
import com.paolobroglio.loxinterpreter.scanner.token.{Token, TokenTypes}

import scala.annotation.tailrec
import scala.util.Try

object IdentifierScanner {

  case class IdentifierResponse(remainingLine: String, lineNumber: Int, accumulatedIdentifier: String)

  private def safelyGetMatchingTokenType(identifier: String): TokenTypes.TokenType =
    Try(TokenTypes.withName(identifier)).getOrElse(TokenTypes.IDENTIFIER)

  def scan(line: String, lineNumber: Int): Either[ScannerMessage, ScanResponse] = {
    @tailrec
    def loop(line: String, lineNumber: Int, acc: String): IdentifierResponse = {
      if (line.isEmpty)
        return IdentifierResponse(line, lineNumber, acc)

      if (isAlphanumeric(line.head))
        loop(line.tail, lineNumber, s"$acc${line.head}")
      else
        IdentifierResponse(line, lineNumber, acc)
    }

    val identifierResponse = loop(line, lineNumber, "")
    val tokenType = safelyGetMatchingTokenType(identifierResponse.accumulatedIdentifier)

    Right(ScanResponse(
      Token(
        tokenType,
        identifierResponse.accumulatedIdentifier,
        null,
        identifierResponse.lineNumber
      ),
      identifierResponse.remainingLine,
      identifierResponse.lineNumber
    ))
  }

  def isAlphabetic(c: Char): Boolean = (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_'
  def isAlphanumeric(c: Char): Boolean = isAlphabetic(c) || NumberScanner.isStrictDigit(c)
}
