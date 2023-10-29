package com.klimov.lab2.lexems;

/**
 * TypeLexeme implementation
 * @author s.a.klimov
 */

/**
 * The {@code TypeLexeme} enum represents different types of lexemes.
 * Lexemes are elements of a mathematical expression that signify operations, variables, numbers, and special markers.
 * This enum defines the various types of lexemes recognized in the expression parsing process.
 */
public enum TypeLexeme {
    PLUS,MINUS, MULTIPLICATION, DIVISION,
    L_BRACKET, R_BRACKET,
    NUMBER,VARIABLE,

    FUNCTION,
    EOF;
}
