package com.klimov.lab2;

import com.klimov.lab2.lexems.LexemAnalyzer;
import com.klimov.lab2.solver.ExpressionSolver;
import com.klimov.lab2.lexems.Lexeme;

import java.util.List;
import java.util.Scanner;

/**
 * Main implementation
 * @author s.a.klimov
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите математическое выражение: ");
        String expressionText = scanner.nextLine();

        LexemAnalyzer lexemAnalyzer = new LexemAnalyzer();
        List<Lexeme> lexemes = lexemAnalyzer.analyze(expressionText);
        ExpressionSolver expressionSolver = new ExpressionSolver(lexemes);
        double result = expressionSolver.solve();
        System.out.println("Резульат: " + result);
    }
}
