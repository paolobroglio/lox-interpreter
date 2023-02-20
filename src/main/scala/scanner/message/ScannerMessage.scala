package com.paolobroglio.loxinterpreter
package scanner.message

object ScannerMessageTypes extends Enumeration{
  type ScannerMessageType = Value

  val ERROR, INFO = Value
}

trait ScannerMessage {
  def `type`: ScannerMessageTypes.ScannerMessageType
}
