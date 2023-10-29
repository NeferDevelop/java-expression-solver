package com.klimov.lab2;

import com.klimov.lab2.exception.UnexpectedElementException;
import com.klimov.lab2.lexems.Lexeme;
import com.klimov.lab2.lexems.TypeLexeme;
import com.klimov.lab2.solver.ExpressionSolver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class contains test cases to verify the functionality of the {@link ExpressionSolver} class.
 * It tests the methods associated with solving mathematical expressions with provided tokens.
 * @author s.a.klimov
 */
public class ExpressionSolverTest {

    /**
     * Verifies the ExpressionSolver with a simple addition expression.
     * Tests if the solver returns the correct result for a simple addition operation.
     */
    @Test
    void testSimpleExpression() {
        // Test addition
        List<Lexeme> tokens = new ArrayList<>();
        tokens.add(new Lexeme(TypeLexeme.NUMBER, "5"));
        tokens.add(new Lexeme(TypeLexeme.PLUS, '+'));
        tokens.add(new Lexeme(TypeLexeme.NUMBER, "3"));
        tokens.add(new Lexeme(TypeLexeme.EOF, ""));
        ExpressionSolver solver = new ExpressionSolver(tokens);
        assertEquals(8, solver.solve());
    }

    /**
     * Verifies the ExpressionSolver with a simple multiplication expression.
     * Tests if the solver returns the correct result for a simple multiplication operation.
     */
    @Test
    public void testSimpleMultiplication() {
        // Test multiplication
        List<Lexeme> tokens = new ArrayList<>();
        tokens.add(new Lexeme(TypeLexeme.NUMBER, "5"));
        tokens.add(new Lexeme(TypeLexeme.MULTIPLICATION, '*'));
        tokens.add(new Lexeme(TypeLexeme.NUMBER, "3"));
        tokens.add(new Lexeme(TypeLexeme.EOF, ""));
        ExpressionSolver solver = new ExpressionSolver(tokens);
        assertEquals(15, solver.solve());
    }

    /**
     * Verifies the handling of division by zero by the ExpressionSolver.
     * Tests if the solver correctly throws an ArithmeticException when division by zero is attempted.
     */
    @Test
    public void testDivisionByZero() {
        List<Lexeme> tokens = new ArrayList<>();
        tokens.add(new Lexeme(TypeLexeme.NUMBER, "5"));
        tokens.add(new Lexeme(TypeLexeme.DIVISION, '/'));
        tokens.add(new Lexeme(TypeLexeme.NUMBER, "0"));
        tokens.add(new Lexeme(TypeLexeme.EOF, ""));
        ExpressionSolver solver = new ExpressionSolver(tokens);
        assertThrows(ArithmeticException.class, solver::solve);
    }

    /**
     * Verifies the handling of an invalid expression by the ExpressionSolver.
     * Tests if the solver correctly throws an UnexpectedElementException for an invalid expression.
     */
    @Test
    public void testInvalidExpression() {
        List<Lexeme> tokens = new ArrayList<>();
        tokens.add(new Lexeme(TypeLexeme.NUMBER, "5"));
        tokens.add(new Lexeme(TypeLexeme.L_BRACKET, '('));
        tokens.add(new Lexeme(TypeLexeme.EOF, ""));
        ExpressionSolver solver = new ExpressionSolver(tokens);
        assertThrows(UnexpectedElementException.class, solver::solve);
    }

    /**
     * Verifies the ExpressionSolver with a complex mathematical expression.
     * Tests if the solver returns the correct result for a more complex mathematical expression.
     */
    @Test
    void testComplexExpression() {
        List<Lexeme> lexemes = new ArrayList<>(Arrays.asList(
                new Lexeme(TypeLexeme.NUMBER, "5"),
                new Lexeme(TypeLexeme.MINUS, "-"),
                new Lexeme(TypeLexeme.L_BRACKET, "("),
                new Lexeme(TypeLexeme.NUMBER, "3"),
                new Lexeme(TypeLexeme.PLUS, "+"),
                new Lexeme(TypeLexeme.NUMBER, "2"),
                new Lexeme(TypeLexeme.R_BRACKET, ")"),
                new Lexeme(TypeLexeme.MULTIPLICATION, "*"),
                new Lexeme(TypeLexeme.NUMBER, "2"),
                new Lexeme(TypeLexeme.EOF, "")
        ));

        ExpressionSolver solver = new ExpressionSolver(lexemes);
        double result = solver.solve();
        assertEquals(-5.0, result);
    }
}
