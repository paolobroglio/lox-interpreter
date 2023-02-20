package com.paolobroglio.loxinterpreter
package scanner.scanners

import scanner.message.Messages.ScannerError
import scanner.scanners.Responses.ScanResponse
import scanner.token.{Token, TokenTypes}

import scala.annotation.tailrec

object StringScanner {
  def scan(line: String, lineNumber: Int): Either[ScannerError, ScanResponse] = {
    @tailrec
    def loop(line: String, lineNumber: Int, acc: String): Either[ScannerError, ScanResponse] = {
      if (line.isEmpty)
        return Left(ScannerError("Unterminated string!", lineNumber, line))

      if (line.head == '"')
        return Right(ScanResponse(Token(TokenTypes.STRING, acc, null, lineNumber), line.tail, lineNumber))

      if (line.head == '\n') {
        loop(line.tail, lineNumber+1, s"$acc\n")
      } else
        loop(line.tail, lineNumber, s"$acc${line.head}")
    }

    loop(line, lineNumber, "")
  }
}
