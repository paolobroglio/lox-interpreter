package com.paolobroglio.loxinterpreter
package scanner.token

object TokenTypes extends Enumeration {
  type TokenType = Value

  val

  LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE, COMMA, DOT,
  MINUS, PLUS, SEMICOLON, SLASH, STAR,

  BANG, BANG_EQUAL, EQUAL, EQUAL_EQUAL, GREATER, GREATER_EQUAL, LESS, LESS_EQUAL,

  IDENTIFIER, STRING, NUMBER, EOF = Value

  val AND = Value("and")
  val CLASS = Value("class")
  val ELSE = Value("else")
  val FALSE = Value("false")
  val FUN = Value("fun")
  val FOR = Value("for")
  val IF = Value("if")
  val OR = Value("or")
  val PRINT = Value("print")
  val RETURN = Value("return")
  val SUPER = Value("super")
  val THIS = Value("this")
  val TRUE = Value("true")
  val VAR = Value("var")
  val WHILE = Value("while")

}
