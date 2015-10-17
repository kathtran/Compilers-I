//----------------------------------------------------------------------
// A starter version of miniJava lexer (manual version). (For CS321 HW1)
//----------------------------------------------------------------------
//
// Kathleen Tran
// Fall 2015
//

import java.io.*;

public class Lexer1 {
    private static FileReader input = null;
    private static int nextC = -1;   // buffer for holding next char
    private static int line = 1;     // currect line position
    private static int column = 0;   // currect column position

    // Internal token code
    //
    enum TokenCode {
        // Tokens with multiple lexemes
        ID, INTLIT, DBLLIT, STRLIT,

        // Keywords
        //   "class", "extends", "static", "public", "main", "void", "boolean",
        //   "int", "double", "String", "true", "false", "new", "this", "if",
        //   "else", "while", "return", "System", "out", "println"
        CLASS, EXTENDS, STATIC, PUBLIC, MAIN, VOID, BOOLEAN, INT, DOUBLE, STRING,
        TRUE, FALSE, NEW, THIS, IF, ELSE, WHILE, RETURN, SYSTEM, OUT, PRINTLN,

        // Operators and delimiters
        //   +, -, *, /, &&, ||, !, ==, !=, <, <=, >, >=, =,
        //   ;, ,, ., (, ), [, ], {, }
        ADD, SUB, MUL, DIV, AND, OR, NOT, EQ, NE, LT, LE, GT, GE, ASSGN,
        SEMI, COMMA, DOT, LPAREN, RPAREN, LBRAC, RBRAC, LCURLY, RCURLY;
    }

    // Token representation
    //
    static class Token {
        TokenCode code;
        String lexeme;
        int line;        // line # of token's first char
        int column;        // column # of token's first char

        public Token(TokenCode code, String lexeme, int line, int column) {
            this.code = code;
            this.lexeme = lexeme;
            this.line = line;
            this.column = column;
        }

        public String toString() {
            return String.format("(%d,%2d) %-10s %s", line, column, code,
                    (code == TokenCode.STRLIT) ? "\"" + lexeme + "\"" : lexeme);
        }
    }

    static void init(FileReader in) throws Exception {
        input = in;
        nextC = input.read();
    }

    //--------------------------------------------------------------------
    // Do not modify the code listed above. Add your code below.
    //

    // Return next char
    //
    // - need to track both line and column numbers
    //
    private static int nextChar() throws Exception {
        int c = nextC;
        nextC = input.read();
        if (c != -1) {
            switch (c) {
                case '\n':
                case '\r':
                    line += 1;
                    column = 0;
                    break;
                default:
                    column += 1;
                    break;
            }
        }
        return c;
    }

    // Returns whether or not input is whitespace
    private static boolean isSpace(int c) {
        return (c == ' ' || c == '\t' || c == '\n' || c == '\r');
    }

    // Returns whether or not input is a letter
    private static boolean isLetter(int c) {
        return ('a' <= c && c <= 'z' || 'A' <= c && c <= 'Z');
    }

    // Returns whether or not input is a digit
    private static boolean isDigit(int c) {
        return ('0' <= c && c <= '9');
    }

    // Returns token for keyword or ID
    private static Token getKeyword(String lexeme, int line, int column) {
        if (lexeme.equals("class"))
            return new Token(TokenCode.CLASS, lexeme, line, column);
        if (lexeme.equals("extends"))
            return new Token(TokenCode.EXTENDS, lexeme, line, column);
        if (lexeme.equals("static"))
            return new Token(TokenCode.STATIC, lexeme, line, column);
        if (lexeme.equals("public"))
            return new Token(TokenCode.PUBLIC, lexeme, line, column);
        if (lexeme.equals("main"))
            return new Token(TokenCode.MAIN, lexeme, line, column);
        if (lexeme.equals("void"))
            return new Token(TokenCode.VOID, lexeme, line, column);
        if (lexeme.equals("boolean"))
            return new Token(TokenCode.BOOLEAN, lexeme, line, column);
        if (lexeme.equals("int"))
            return new Token(TokenCode.INT, lexeme, line, column);
        if (lexeme.equals("double"))
            return new Token(TokenCode.DOUBLE, lexeme, line, column);
        if (lexeme.equals("String"))
            return new Token(TokenCode.STRING, lexeme, line, column);
        if (lexeme.equals("true"))
            return new Token(TokenCode.TRUE, lexeme, line, column);
        if (lexeme.equals("false"))
            return new Token(TokenCode.FALSE, lexeme, line, column);
        if (lexeme.equals("new"))
            return new Token(TokenCode.NEW, lexeme, line, column);
        if (lexeme.equals("this"))
            return new Token(TokenCode.THIS, lexeme, line, column);
        if (lexeme.equals("if"))
            return new Token(TokenCode.IF, lexeme, line, column);
        if (lexeme.equals("else"))
            return new Token(TokenCode.ELSE, lexeme, line, column);
        if (lexeme.equals("while"))
            return new Token(TokenCode.WHILE, lexeme, line, column);
        if (lexeme.equals("return"))
            return new Token(TokenCode.RETURN, lexeme, line, column);
        if (lexeme.equals("System"))
            return new Token(TokenCode.SYSTEM, lexeme, line, column);
        if (lexeme.equals("out"))
            return new Token(TokenCode.OUT, lexeme, line, column);
        if (lexeme.equals("println"))
            return new Token(TokenCode.PRINTLN, lexeme, line, column);
        return new Token(TokenCode.ID, lexeme, line, column);
    }

    // Return next token (the main lexer routine)
    //
    // - need to capture the line and column numbers of the first char
    //   of each token
    //
    static Token nextToken() throws Exception {
        int c = nextChar();

        do {
            // skip whitespace
            while (isSpace(c))
                c = nextChar();

            // skip comments
            if (c == '/') {
                if (nextC == '/') {                 // recognize single line comment
                    do {
                        c = nextChar();
                    } while (c != '\n' && c != -1);
                } else if (nextC == '*') {                 // recognize block comment
                    do {
                        c = nextChar();
                        if (c == '*') {
                            c = nextChar();
                            if (c == '/') {
                                c = nextChar();
                            }
                        }
                    } while (c != -1);
                }
            }
        } while (isSpace(c));

        // reached <EOF>
        if (c == -1)
            return null;

        int firstCharColumn = column;

        // recognize ID and keywords
        if (isLetter(c)) {
            StringBuilder buffer = new StringBuilder();
            buffer.append((char) c);
            while (isLetter((char) c)) {
                c = nextChar();
                buffer.append((char) c);
            }
            String lexeme = buffer.toString();
            return getKeyword(lexeme, line, column);
        }

        // recognize double literals
        // case 1: leading dot
        if (c == '.') {
            StringBuilder buffer = new StringBuilder();
            buffer.append((char) c);
            c = nextChar();
            if (isSpace(c) || !isDigit(c))
                return new Token(TokenCode.DOT, ".", line, column);
            while (isDigit(c)) {
                buffer.append((char) c);
                c = nextChar();
                if (c == '.')
                    throw new Exception("Double Literal Error on " + line + " on column " + firstCharColumn +
                            ": Double literal value cannot contain more than one dot operator.");
            }
            String lexeme = buffer.toString();
            try {
                Double.parseDouble(lexeme);
                return new Token(TokenCode.DBLLIT, lexeme, line, column);
            } catch (NumberFormatException ex) {
                throw new Exception("Double Literal Error on " + line + " on column " + firstCharColumn +
                        ": Invalid double literal value " + lexeme);
            }
        }

        // recognize integer literals
        if (isDigit(c)) {
            StringBuilder buffer = new StringBuilder();
            buffer.append((char) c);
            // octal literal
            if (c == '0') {
                // integer literal if SINGLETON
                if (isSpace(nextC)) {
                    String lexeme = buffer.toString();
                    try {
                        Integer integer = Integer.parseInt(lexeme);
                        if (0 <= integer && integer <= 2147483647)
                            return new Token(TokenCode.INTLIT, integer.toString(), line, column);
                    } catch (Exception ex) {
                        throw new Exception("Integer Literal Error on " + line + " on column " + firstCharColumn +
                                ": Invalid integer literal value " + (char) c);
                    }
                }
                // hexadecimal literal
                if (nextC == 'x' || nextC == 'X') {
                    do {
                        c = nextChar();
                        buffer.append((char) c);
                    } while (isDigit(c) && c != -1 && c != '\n');
                    String lexeme = buffer.toString();
                    try {
                        return new Token(TokenCode.INTLIT, (Integer.decode(lexeme)).toString(), line, column);
                    } catch (NumberFormatException ex) {
                        throw new Exception("Integer Literal Error on " + line + " on column " + firstCharColumn +
                                ": Invalid hexadecimal literal value " + lexeme);
                    }
                }
                do {
                    c = nextChar();
                    buffer.append((char) c);
                } while (isDigit(c) && c != -1 && c != '\n');
                String lexeme = buffer.toString();
                try {
                    Integer octal = Integer.parseInt(lexeme, 8);
                    return new Token(TokenCode.INTLIT, octal.toString(), line, column);
                } catch (NumberFormatException ex) {
                    throw new Exception("Integer Literal Error on " + line + " on column " + firstCharColumn +
                            ": Invalid octal literal value " + (char) c);
                }
            }
            // integer literal
            while (isDigit(c)) {
                c = nextChar();
                buffer.append((char) c);
            }
            String lexeme = buffer.toString();
            try {
                Integer integer = Integer.parseInt(lexeme);
                if (0 <= integer && integer <= 2147483647)
                    return new Token(TokenCode.INTLIT, integer.toString(), line, column);
            } catch (NumberFormatException ex) {
                throw new Exception("Integer Literal Error on " + line + " on column " + firstCharColumn +
                        ": Invalid integer literal value " + (char) c);
            }
        }

        // recognize string literals
        if (c == '"') {
            StringBuilder buffer = new StringBuilder();
            do {
                c = nextChar();
                if (c == '"')
                    break;

                buffer.append((char) c);
            } while (c != -1 && c != '\n' && c != '\r');
            String lexeme = buffer.toString();
            return new Token(TokenCode.STRLIT, lexeme, line, column);
        }

        // recognize operators, delimiters, and comments
        switch (c) {
            case '+':
                return new Token(TokenCode.ADD, "+", line, column);
            case '-':
                return new Token(TokenCode.SUB, "-", line, column);
            case '*':
                return new Token(TokenCode.MUL, "*", line, column);
            case '/':
                return new Token(TokenCode.DIV, "/", line, column);
            case '&':
                if (nextC == '&') {
                    c = nextChar();
                    return new Token(TokenCode.AND, "&&", line, column - 1);
                }
            case '|':
                if (nextC == '|') {
                    c = nextChar();
                    return new Token(TokenCode.OR, "||", line, column - 1);
                }
            case '!':
                if (nextC == '=') {
                    c = nextChar();
                    return new Token(TokenCode.NE, "!=", line, column - 1);
                }
                return new Token(TokenCode.NOT, "!", line, column);
            case '=':
                if (nextC == '=') {
                    c = nextChar();
                    return new Token(TokenCode.EQ, "==", line, column - 1);
                }
                return new Token(TokenCode.ASSGN, "=", line, column);
            case '<':
                if (nextC == '=') {
                    c = nextChar();
                    return new Token(TokenCode.LE, "<=", line, column - 1);
                }
                return new Token(TokenCode.LT, "<", line, column);
            case '>':
                if (nextC == '=') {
                    c = nextChar();
                    return new Token(TokenCode.GE, ">=", line, column - 1);
                }
                return new Token(TokenCode.GT, ">", line, column);
            case ';':
                return new Token(TokenCode.SEMI, ";", line, column);
            case ',':
                return new Token(TokenCode.COMMA, ",", line, column);
            case '.':
                return new Token(TokenCode.DOT, ".", line, column);
            case '(':
                return new Token(TokenCode.LPAREN, "(", line, column);
            case ')':
                return new Token(TokenCode.RPAREN, ")", line, column);
            case '[':
                return new Token(TokenCode.LBRAC, "[", line, column);
            case ']':
                return new Token(TokenCode.RBRAC, "]", line, column);
            case '{':
                return new Token(TokenCode.LCURLY, "{", line, column);
            case '}':
                return new Token(TokenCode.RCURLY, "}", line, column);
        }

        throw new Exception("Lexical Error on line " + line + " on column " + firstCharColumn +
                ": Illegal character " + (char) c);
    }

}
