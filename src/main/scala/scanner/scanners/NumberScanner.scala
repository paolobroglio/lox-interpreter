package com.paolobroglio.loxinterpreter
package scanner.scanners

import com.paolobroglio.loxinterpreter.scanner.message.ScannerMessage
import com.paolobroglio.loxinterpreter.scanner.scanners.Responses.ScanResponse
import com.paolobroglio.loxinterpreter.scanner.token.{Token, TokenTypes}

import scala.annotation.tailrec

object NumberScanner {

  case class DigitResponse(remainingLine: String, accumulatedNumber: String) {
    override def toString: String = s"remainingLine: $remainingLine, accumulatedNumber: $accumulatedNumber"
  }

  def isStrictDigit(c: Char): Boolean = c >= '0' && c <= '9'

  def scan(line: String, lineNumber: Int): Either[ScannerMessage, ScanResponse] = {
    @tailrec
    def getDigits(line: String, lineNumber: Int, acc: String): DigitResponse = {
      if (line.isEmpty)
        return DigitResponse(line,acc)

      if (!isStrictDigit(line.head))
        DigitResponse(line,acc)
      else
        getDigits(line.tail, lineNumber, s"$acc${line.head}")
    }

    val numberDigitResponse = getDigits(line, lineNumber, "")

    if (numberDigitResponse.remainingLine.nonEmpty && numberDigitResponse.remainingLine.head == '.') {
      println(numberDigitResponse)
      val fractionalDigitResponse = getDigits(numberDigitResponse.remainingLine.tail, lineNumber, "")
      val finalNumber = s"${numberDigitResponse.accumulatedNumber}.${fractionalDigitResponse.accumulatedNumber}"

      return Right(ScanResponse(
        Token(
          TokenTypes.NUMBER,
          finalNumber,
          finalNumber.toDouble,
          lineNumber),
        fractionalDigitResponse.remainingLine,
        lineNumber))
    }

    Right(ScanResponse(
      Token(
        TokenTypes.NUMBER,
        numberDigitResponse.accumulatedNumber,
        numberDigitResponse.accumulatedNumber.toDouble,
        lineNumber),
      numberDigitResponse.remainingLine,
      lineNumber))
  }
}
