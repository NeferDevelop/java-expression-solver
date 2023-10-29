package com.klimov.lab2.solver;

import com.klimov.lab2.exception.UnexpectedElementException;
import com.klimov.lab2.lexems.Lexeme;
import com.klimov.lab2.lexems.LexemeBuffer;
import com.klimov.lab2.lexems.TypeLexeme;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * ExpressionSolver implementation
 * @author s.a.klimov
 */
public class ExpressionSolver {
    private LexemeBuffer lexemeBuffer;
    private Map<String, Double> variables;

    /**
     * Constructs an ExpressionSolver object with a list of lexemes.
     *
     * @param lexemes The list of lexemes parsed from the mathematical expression.
     */
    public ExpressionSolver(List<Lexeme> lexemes) {
        lexemeBuffer = new LexemeBuffer(lexemes);
        variables = new HashMap<>();
    }

    /**
     * Initiates the evaluation of the mathematical expression.
     *
     * @return The result of the mathematical expression after evaluation.
     */
    public double solve() {
        return expressions();
    }

    /**
     * Parses and evaluates the addition and subtraction operations in the expression.
     *
     * @return The calculated result after addition and subtraction operations.
     */
    private double expressions() {
        if (lexemeBuffer.next().getType() == TypeLexeme.EOF) {
            return 0;
        } else {
            lexemeBuffer.back();
            return plusMinus();
        }
    }

    /**
     * Handles addition and subtraction operations.
     *
     * @return The result of the addition and subtraction operations.
     */
    private double plusMinus() {
        double value = multDiv();
        while (true) {
            Lexeme lexeme = lexemeBuffer.next();
            switch (lexeme.getType()) {
                case PLUS:
                    value += multDiv();
                    break;
                case MINUS:
                    value -= multDiv();
                    break;
                case EOF:
                case R_BRACKET:
                    lexemeBuffer.back();
                    return value;
                default:
                    throw new UnexpectedElementException("Unexpected token: " + lexeme.getValue()
                            + " at position: " + lexemeBuffer.getPosition());
            }
        }
    }

    /**
     * Handles multiplication and division operations.
     *
     * @return The result of the multiplication and division operations.
     */
    private double multDiv() {
        double value = factor();
        while (true) {
            Lexeme lexeme = lexemeBuffer.next();
            switch (lexeme.getType()) {
                case MULTIPLICATION:
                    value *= factor();
                    break;
                case DIVISION:
                    double divisor = factor();
                    if (divisor == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    value /= divisor;
                    break;
                case EOF:
                case R_BRACKET:
                case PLUS:
                case MINUS:
                    lexemeBuffer.back();
                    return value;
                default:
                    throw new UnexpectedElementException("Unexpected token: " + lexeme.getValue() + " at position: " + lexemeBuffer.getPosition());
            }
        }
    }

    /**
     * Evaluates the factors including numbers, variables, and function calls.
     *
     * @return The result of evaluating the factors within the expression.
     */
    private double factor() {
        Lexeme lexeme = lexemeBuffer.next();
        switch (lexeme.getType()) {
            case MINUS:
                return -factor();
            case NUMBER:
                return Double.parseDouble(lexeme.getValue());
            case VARIABLE:
                String varName = lexeme.getValue();
                if (!variables.containsKey(varName)) {
                    double varValue = requestVariableValue(varName);
                    variables.put(varName, varValue);
                }
                return variables.get(varName);
            case L_BRACKET:
                double value = plusMinus();
                lexeme = lexemeBuffer.next();
                if (lexeme.getType() != TypeLexeme.R_BRACKET) {
                    throw new UnexpectedElementException("Unexpected token: " + lexeme.getValue()
                            + " at position: " + lexemeBuffer.getPosition());
                }
                return value;
            case FUNCTION:
                String functionName = lexeme.getValue();
                double argument = factor();
                return executeFunction(functionName, argument);
            default:
                throw new UnexpectedElementException("Unexpected token: " + lexeme.getValue()
                        + " at position: " + lexemeBuffer.getPosition());
        }
    }


    /**
     * Requests user input for the variable's value.
     *
     * @param varName The name of the variable for which the value is requested.
     * @return The value entered by the user for the specified variable.
     */
    private double requestVariableValue(String varName) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите значение для переменной " + varName + " : ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Пожалуйста, введите допустимое значение.");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    /**
     * Executes the specified mathematical function on the given argument.
     *
     * @param functionName The name of the mathematical function to execute.
     * @param argument The argument on which the function is applied.
     * @return The result after applying the function on the argument.
     */
    private double executeFunction(String functionName, double argument) {
        switch (functionName) {
            case "sin":
                return Math.sin(argument);
            case "cos":
                return Math.cos(argument);
            case "tan":
                return Math.tan(argument);
            case "cot":
                return 1.0 / Math.tan(argument);
            default:
                throw new RuntimeException("Неподдерживаемая функция: " + functionName);
        }
    }
}
