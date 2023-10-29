package com.klimov.lab2.lexems;

/**
 * Lexeme implementation
 * @author s.a.klimov
 */
public class Lexeme {
    TypeLexeme type;
    String value;

    /**
     * Constructs a Lexeme object with a specified type and value.
     *
     * @param type  The type of the lexeme.
     * @param value The value associated with the lexeme.
     */
    public Lexeme(TypeLexeme type, String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Constructs a Lexeme object with a specified type and character value.
     *
     * @param type  The type of the lexeme.
     * @param value The character value associated with the lexeme.
     */
    public Lexeme(TypeLexeme type, Character value) {
        this.type = type;
        this.value = value.toString();
    }

    /**
     * Retrieves the type of the lexeme.
     *
     * @return The type of the lexeme.
     */
    public TypeLexeme getType() {
        return type;
    }

    /**
     * Retrieves the value associated with the lexeme.
     *
     * @return The value of the lexeme.
     */
    public String getValue() {
        return value;
    }
}
