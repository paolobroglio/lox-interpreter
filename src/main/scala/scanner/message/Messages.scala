package com.paolobroglio.loxinterpreter
package scanner.message

object Messages {
  case class ScannerError(message: String, lineNumber: Int, remainingLine: String) extends ScannerMessage {
    override def `type`: ScannerMessageTypes.ScannerMessageType = ScannerMessageTypes.ERROR
    override def toString: String = s"Error detected: message: $message, line: $lineNumber"
  }

  case class ScannerSkippedMessage(unicodeValue: Long, lineNumber: Int, remainingLine: String) extends ScannerMessage {
    override def `type`: ScannerMessageTypes.ScannerMessageType = ScannerMessageTypes.INFO
    override def toString: String = s"Skipping character: char: $unicodeValue, line: $lineNumber"
  }

  case class ScannerSkippedComment(lineNumber: Int, remainingLine: String) extends ScannerMessage {
    override def `type`: ScannerMessageTypes.ScannerMessageType = ScannerMessageTypes.INFO
    override def toString: String = s"Skipping comment: line: $lineNumber"
  }
}
