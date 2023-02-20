package com.paolobroglio.loxinterpreter
package scanner

import scanner.message.Messages.{ScannerError, ScannerSkippedComment, ScannerSkippedMessage}
import scanner.message.ScannerMessage
import scanner.scanners.Responses.ScanResponse
import scanner.scanners.{CommentScanner, IdentifierScanner, NumberScanner, StringScanner, TokenScanner}
import scanner.token.{Token, TokenTypes}

import scala.annotation.tailrec

object Scanner {

  def scanToken(line: String, lineNumber: Int): Either[ScannerMessage, ScanResponse] = {
    line.head match {
        case '(' =>
          Right(ScanResponse(Token(TokenTypes.LEFT_PAREN, "(", null, lineNumber), line.tail, lineNumber))
        case ')' =>
          Right(ScanResponse(Token(TokenTypes.RIGHT_PAREN, ")", null, lineNumber), line.tail, lineNumber))
        case '{' =>
          Right(ScanResponse(Token(TokenTypes.LEFT_BRACE, "{", null, lineNumber), line.tail, lineNumber))
        case '}' =>
          Right(ScanResponse(Token(TokenTypes.RIGHT_BRACE, "}", null, lineNumber), line.tail, lineNumber))
        case ',' =>
          Right(ScanResponse(Token(TokenTypes.COMMA, ",", null, lineNumber), line.tail, lineNumber))
        case '.' =>
          Right(ScanResponse(Token(TokenTypes.DOT, ".", null, lineNumber), line.tail, lineNumber))
        case '-' =>
          Right(ScanResponse(Token(TokenTypes.MINUS, "-", null, lineNumber), line.tail, lineNumber))
        case '+' =>
          Right(ScanResponse(Token(TokenTypes.PLUS, "+", null, lineNumber), line.tail, lineNumber))
        case ';' =>
          Right(ScanResponse(Token(TokenTypes.SEMICOLON, ";", null, lineNumber), line.tail, lineNumber))
        case '*' =>
          Right(ScanResponse(Token(TokenTypes.STAR, "*", null, lineNumber), line.tail, lineNumber))
        case '!' =>
          Right(TokenScanner.scan(TokenTypes.BANG, TokenTypes.BANG_EQUAL, '=', line, lineNumber))
        case '=' =>
          Right(TokenScanner.scan(TokenTypes.EQUAL, TokenTypes.EQUAL_EQUAL, '=', line, lineNumber))
        case '<' =>
          Right(TokenScanner.scan(TokenTypes.LESS, TokenTypes.LESS_EQUAL, '=', line, lineNumber))
        case '>' =>
          Right(TokenScanner.scan(TokenTypes.GREATER, TokenTypes.GREATER_EQUAL, '=',  line, lineNumber))
        case '/' =>
          CommentScanner.scan(line.tail, lineNumber)
        case '"' =>
          StringScanner.scan(line.tail, lineNumber)
        case c if NumberScanner.isStrictDigit(c) =>
          NumberScanner.scan(line, lineNumber)
        case c if IdentifierScanner.isAlphabetic(c) =>
          IdentifierScanner.scan(line, lineNumber)
        case '\n' =>
          Left(ScannerSkippedMessage('\n', lineNumber+1, line.tail))
        case c if List(' ', '\r', '\t').contains(c) =>
          Left(ScannerSkippedMessage(c, lineNumber, line.tail))
        case c =>
          Left(ScannerError(s"Unrecognized character '${c}'", lineNumber, line.tail))
      }
  }

  def scanTokens(line: String, lineNumber: Int): List[Token] = {
    @tailrec
    def loop(line: String, acc: List[Token], lineNumber: Int): List[Token] = {

      if (line.isEmpty)
        return acc :+ Token(TokenTypes.EOF, "", null, lineNumber)

      scanToken(line, lineNumber) match {
        case Right(scanResponse) =>
          loop(scanResponse.remainingLine, acc :+ scanResponse.token, scanResponse.lineNumber)
        case Left(message: ScannerSkippedMessage) =>
          println(message)
          loop(message.remainingLine, acc, message.lineNumber)
        case Left(message: ScannerSkippedComment) =>
          println(message)
          loop(message.remainingLine, acc, message.lineNumber)
        case Left(error: ScannerError) =>
          // todo: stop scanning
          println(error)
          loop(error.remainingLine, acc, error.lineNumber)
      }
    }

    loop(line, List.empty, lineNumber)
  }
}
