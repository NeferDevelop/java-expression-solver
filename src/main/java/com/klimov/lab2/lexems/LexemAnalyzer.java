package com.klimov.lab2.lexems;

import com.klimov.lab2.exception.UnexpectedElementException;

import java.util.ArrayList;
import java.util.List;

/**
 * LexemAnalyzer implementation
 * @author s.a.klimov
 */
public class LexemAnalyzer {

    /**
     * Analyzes the given mathematical expression and generates a list of lexemes.
     *
     * @param textExpression The mathematical expression to be analyzed.
     * @return A list of lexemes parsed from the expression.
     * @throws UnexpectedElementException If an unexpected character is encountered.
     */
    public static List<Lexeme> analyze(String textExpression) {
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        int position = 0;
        while (position < textExpression.length()) {
            char symbol = textExpression.charAt(position);
            switch (symbol) {
                case '(':
                    lexemes.add(new Lexeme(TypeLexeme.L_BRACKET, symbol));
                    position++;
                    break;
                case ')':
                    lexemes.add(new Lexeme(TypeLexeme.R_BRACKET, symbol));
                    position++;
                    break;
                case '+':
                    lexemes.add(new Lexeme(TypeLexeme.PLUS, symbol));
                    position++;
                    break;
                case '-':
                    lexemes.add(new Lexeme(TypeLexeme.MINUS, symbol));
                    position++;
                    break;
                case '*':
                    lexemes.add(new Lexeme(TypeLexeme.MULTIPLICATION, symbol));
                    position++;
                    break;
                case '/':
                    lexemes.add(new Lexeme(TypeLexeme.DIVISION, symbol));
                    position++;
                    break;
                default:
                    position = processCharacter(textExpression, position, symbol, lexemes);
            }
        }
        lexemes.add(new Lexeme(TypeLexeme.EOF, ""));
        return lexemes;
    }

    /**
     * Processes a character based on its type in the given expression.
     *
     * @param textExpression The mathematical expression.
     * @param position       The current position in the expression.
     * @param symbol         The character to be processed.
     * @param lexemes        The list of lexemes.
     * @return The updated position after processing the character.
     * @throws UnexpectedElementException If an unexpected character is encountered.
     */
    private static int processCharacter(String textExpression, int position, char symbol, List<Lexeme> lexemes) {
        if (Character.isLetter(symbol)) {
            return processLetters(textExpression, position, lexemes);
        } else if (Character.isDigit(symbol) || symbol == '.') {
            return processNumbers(textExpression, position, lexemes);
        } else {
            if (symbol != ' ') {
                throw new UnexpectedElementException("Unexpected character: " + symbol);
            }
            return ++position;
        }
    }

    /**
     * Processes letters (variables or functions) in the given expression.
     *
     * @param textExpression The mathematical expression.
     * @param position       The current position in the expression.
     * @param lexemes        The list of lexemes.
     * @return The updated position after processing the letters.
     */
    private static int processLetters(String textExpression, int position, List<Lexeme> lexemes) {
        StringBuilder varBuilder = new StringBuilder();
        varBuilder.append(textExpression.charAt(position));
        position++;

        while (position < textExpression.length() &&
                (Character.isLetterOrDigit(textExpression.charAt(position)) || textExpression.charAt(position) == '_')) {
            varBuilder.append(textExpression.charAt(position));
            position++;
        }

        String var = varBuilder.toString();
        if (var.equals("sin") || var.equals("cos") || var.equals("tan") || var.equals("cot")) {
            lexemes.add(new Lexeme(TypeLexeme.FUNCTION, var));
        } else {
            lexemes.add(new Lexeme(TypeLexeme.VARIABLE, var));
        }

        return position;
    }

    /**
     * Processes numbers (constants) in the given expression.
     *
     * @param textExpression The mathematical expression.
     * @param position       The current position in the expression.
     * @param lexemes        The list of lexemes.
     * @return The updated position after processing the numbers.
     */
    private static int processNumbers(String textExpression, int position, List<Lexeme> lexemes) {
        StringBuilder numBuilder = new StringBuilder();
        numBuilder.append(textExpression.charAt(position));
        position++;

        boolean hasDecimal = false;
        while (position < textExpression.length()) {
            char currentChar = textExpression.charAt(position);
            if (Character.isDigit(currentChar)) {
                numBuilder.append(currentChar);
                position++;
            } else if (currentChar == '.' && !hasDecimal) {
                numBuilder.append(currentChar);
                hasDecimal = true;
                position++;
            } else {
                break;
            }
        }

        lexemes.add(new Lexeme(TypeLexeme.NUMBER, numBuilder.toString()));
        return position;
    }

}
