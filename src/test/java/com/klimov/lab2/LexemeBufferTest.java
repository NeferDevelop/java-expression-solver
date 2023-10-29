package com.klimov.lab2;

import com.klimov.lab2.lexems.Lexeme;
import com.klimov.lab2.lexems.LexemeBuffer;
import com.klimov.lab2.lexems.TypeLexeme;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains test cases to verify the functionality of the {@link LexemeBuffer} class.
 * It tests the navigation methods and positions of the LexemeBuffer for different scenarios.
 * @author s.a.klimov
 */
public class LexemeBufferTest {

    /**
     * Verifies the behavior of the {@link LexemeBuffer#next()} and {@link LexemeBuffer#back()} methods.
     * It checks the movement through the list of Lexemes and the correct position tracking using the back method.
     */
    @Test
    public void testNextAndBack() {
        List<Lexeme> lexemes = new ArrayList<>();
        lexemes.add(new Lexeme(TypeLexeme.NUMBER, "42"));
        lexemes.add(new Lexeme(TypeLexeme.PLUS, '+'));
        lexemes.add(new Lexeme(TypeLexeme.NUMBER, "10"));

        LexemeBuffer buffer = new LexemeBuffer(lexemes);

        assertEquals(0, buffer.getPosition());
        assertEquals(TypeLexeme.NUMBER, buffer.next().getType());
        assertEquals(1, buffer.getPosition());
        assertEquals(TypeLexeme.PLUS, buffer.next().getType());
        assertEquals(2, buffer.getPosition());

        buffer.back();
        assertEquals(1, buffer.getPosition());
        assertEquals(TypeLexeme.PLUS, buffer.next().getType());
        assertEquals(2, buffer.getPosition());
    }

    /**
     * Verifies the initial position of the {@link LexemeBuffer} when created with a list of Lexemes.
     */
    @Test
    public void testGetInitialPosition() {
        List<Lexeme> lexemes = new ArrayList<>();
        lexemes.add(new Lexeme(TypeLexeme.NUMBER, "5"));
        lexemes.add(new Lexeme(TypeLexeme.MINUS, '-'));

        LexemeBuffer buffer = new LexemeBuffer(lexemes);

        assertEquals(0, buffer.getPosition());
    }

    /**
     * Verifies the position after traversing through the list of Lexemes using the {@link LexemeBuffer#next()} method.
     */
    @Test
    public void testGetPositionAfterTraversal() {
        List<Lexeme> lexemes = new ArrayList<>();
        lexemes.add(new Lexeme(TypeLexeme.VARIABLE, "x"));
        lexemes.add(new Lexeme(TypeLexeme.EOF, ""));

        LexemeBuffer buffer = new LexemeBuffer(lexemes);
        buffer.next();

        assertEquals(1, buffer.getPosition());
    }
}
