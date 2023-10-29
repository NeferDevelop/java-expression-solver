package com.klimov.lab2;

import com.klimov.lab2.exception.UnexpectedElementException;
import com.klimov.lab2.lexems.Lexeme;
import com.klimov.lab2.lexems.LexemAnalyzer;
import com.klimov.lab2.lexems.TypeLexeme;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This class contains test cases to verify the functionality of the {@link LexemAnalyzer} class.
 * It tests the analysis of mathematical expressions into a list of lexemes.
 * @author s.a.klimov
 */
public class LexemAnalyzerTest {

    /**
     * Verifies the analysis of a basic mathematical expression.
     * It checks whether the given expression is correctly converted into a list of lexemes.
     */
    @Test
    public void testBasicMathematicalExpression() {
        String expression = "3 * (5 - 2) + sin(x)";

        List<Lexeme> lexemes = LexemAnalyzer.analyze(expression);

        assertEquals(TypeLexeme.NUMBER, lexemes.get(0).getType());
        assertEquals("*", lexemes.get(1).getValue());
        assertEquals(TypeLexeme.L_BRACKET, lexemes.get(2).getType());
        assertEquals(TypeLexeme.NUMBER, lexemes.get(3).getType());
        assertEquals("-", lexemes.get(4).getValue());
        assertEquals(TypeLexeme.NUMBER, lexemes.get(5).getType());
        assertEquals(TypeLexeme.R_BRACKET, lexemes.get(6).getType());
        assertEquals("+", lexemes.get(7).getValue());
        assertEquals(TypeLexeme.FUNCTION, lexemes.get(8).getType());
        assertEquals(TypeLexeme.L_BRACKET, lexemes.get(9).getType());
        assertEquals(TypeLexeme.VARIABLE, lexemes.get(10).getType());
        assertEquals(TypeLexeme.R_BRACKET, lexemes.get(11).getType());
    }

    /**
     * Verifies the handling of an unexpected character in the expression.
     * It tests if an UnexpectedElementException is thrown when an unexpected character is present in the expression.
     */
    @Test
    public void testUnexpectedCharacter() {
        String expression = "2 # 5"; // Incorrect character #

        assertThrows(UnexpectedElementException.class, () -> LexemAnalyzer.analyze(expression));
    }
}
