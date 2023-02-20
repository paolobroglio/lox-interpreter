package com.paolobroglio.loxinterpreter
package scanner.scanners

import scanner.token.Token

object Responses {
  case class ScanResponse(token: Token, remainingLine: String, lineNumber: Int)
}
