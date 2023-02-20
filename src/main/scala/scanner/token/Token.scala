package com.paolobroglio.loxinterpreter
package scanner.token

import scala.util.Try


object Token {
  def fromTypeAndText(`type`: TokenTypes.TokenType, text: String, start: Int, end: Int, line: Int): Option[Token] =
    Try(text.substring(start, end))
      .map(lexeme => Token(`type`, lexeme, null, line))
      .toOption
}

case class Token(
                  `type`: TokenTypes.TokenType,
                  lexeme: String,
                  literal: Any,
                  line: Int
                ) {
  override def toString: String = s"${`type`} $lexeme $literal"
}
