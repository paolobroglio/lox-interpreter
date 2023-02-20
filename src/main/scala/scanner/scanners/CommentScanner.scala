package com.paolobroglio.loxinterpreter
package scanner.scanners

import scanner.message.Messages.ScannerSkippedComment
import scanner.message.ScannerMessage
import scanner.scanners.Responses.ScanResponse
import scanner.token.{Token, TokenTypes}

import scala.annotation.tailrec

object CommentScanner {

  def scan(line: String, lineNumber: Int): Either[ScannerMessage, ScanResponse] = {
    @tailrec
    def loop(line: String): Either[ScannerMessage, ScanResponse] = {
      if (line.isEmpty)
        return Left(ScannerSkippedComment(lineNumber, line))

      if (line.head == '\n')
        Left(ScannerSkippedComment(lineNumber, line.tail))
      else
        loop(line.tail)
    }

    if (line.isEmpty || line.head != '/')
      Right(ScanResponse(Token(TokenTypes.SLASH, "/", null, lineNumber), line, lineNumber))
    else
      loop(line.tail)
  }
}
