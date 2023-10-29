package com.klimov.lab2;

import com.klimov.lab2.lexems.Lexeme;
import com.klimov.lab2.lexems.TypeLexeme;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains test cases to verify the functionality of the {@link Lexeme} class.
 * It tests various constructors, as well as the getType() and getValue() methods of Lexeme.
 * @author s.a.klimov
 */
public class LexemeTest {

    /**
     * Verifies the constructor of Lexeme that takes a TypeLexeme and a value as a string.
     * It checks if the returned type and value match the provided values in the constructor.
     */
    @Test
    public void testLexemeConstructionWithValue() {
        Lexeme lexeme = new Lexeme(TypeLexeme.NUMBER, "42");
        assertEquals(TypeLexeme.NUMBER, lexeme.getType());
        assertEquals("42", lexeme.getValue());
    }

    /**
     * Verifies the constructor of Lexeme that takes a TypeLexeme and a character value.
     * It ensures that the returned type and value match the provided values in the constructor.
     */
    @Test
    public void testLexemeConstructionWithCharacterValue() {
        Lexeme lexeme = new Lexeme(TypeLexeme.PLUS, '+');
        assertEquals(TypeLexeme.PLUS, lexeme.getType());
        assertEquals("+", lexeme.getValue());
    }

    /**
     * Verifies the {@link Lexeme#getType()} method.
     * It checks if the returned type matches the TypeLexeme provided in the constructor.
     */
    @Test
    public void testLexemeGetType() {
        Lexeme lexeme = new Lexeme(TypeLexeme.VARIABLE, "x");
        assertEquals(TypeLexeme.VARIABLE, lexeme.getType());
    }

    /**
     * Verifies the {@link Lexeme#getValue()} method.
     * It ensures that the returned value matches the string value provided in the constructor.
     */
    @Test
    public void testLexemeGetValue() {
        Lexeme lexeme = new Lexeme(TypeLexeme.MULTIPLICATION, "*");
        assertEquals("*", lexeme.getValue());
    }
}
