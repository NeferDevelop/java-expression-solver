package com.klimov.lab2.lexems;

import java.util.List;

/**
 * LexemBuffer implementation
 * @author s.a.klimov
 */
public class LexemeBuffer {
    private int position;
    public List<Lexeme> lexemes;

    /**
     * Constructs a LexemeBuffer with a given list of lexemes.
     *
     * @param lexemes The list of lexemes to be buffered.
     */
    public LexemeBuffer(List<Lexeme> lexemes) {
        this.lexemes = lexemes;
    }

    /**
     * Retrieves the next lexeme in the buffer.
     *
     * @return The next lexeme.
     */
    public Lexeme next(){
        return lexemes.get(position++);
    }

    /**
     * Moves the buffer position backward by one.
     */
    public void back(){
        position--;
    }

    /**
     * Retrieves the current position in the buffer.
     *
     * @return The current position in the buffer.
     */
    public int getPosition(){
        return position;
    }
}
