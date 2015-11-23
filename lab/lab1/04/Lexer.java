// This is supporting software for CS321/CS322 Compilers and Language Design.
// Copyright (c) Portland State University.
//---------------------------------------------------------------------------
// For CS321 F'15 (J. Li).
//

// A lexer for the regular expression language, RE.
//
// RE Tokens:
//   <LETTER: ['a'-'z']>     
//   <ALTER:  '|'>
//   <REPEAT: '*'>
//   <LPAREN: '('>
//   <RPAREN: ')'>
//   <END:    '\n'>
//
import java.io.*;

public class Lexer {
  private static FileReader input = null;
  private static int pos = 0;	// currect lexing position

  // Internal token code
  static final int LETTER = 1;
  static final int ALTER  = 2;
  static final int REPEAT = 3;
  static final int LPAREN = 4;
  static final int RPAREN = 5;
  static final int END    = 0;

  // Token representation
  //
  static class Token {
    int code;   // code
    char lex;   // lexeme
    int pos;    // position
  
    public Token(int code, char lex, int pos) {
      this.code = code; this.lex = lex; this.pos = pos;  
    }
    public String toString() {
      return String.format("(%02d) [%d]\t%s", pos, code, (lex=='\n'? "\\n" : lex));
    }
  }

  // Utility routines
  //
  static void init(FileReader in) { input = in; }

  private static boolean isSpace(int c) {
    return (c == ' ' || c == '\t' || c == '\r');
  }

  private static boolean isLetter(int c) {
    return ('a' <= c && c <= 'z');
  }

  // Return next char
  //
  private static int nextChar() throws Exception {
    int c = input.read();
    if (c != -1)
      pos++;
    return c;
  }

  // The main lexer routine - to be called by parser
  // - skip white space
  // - detect lexical error
  // - return a valid token
  //
  static Token nextToken() throws Exception {
    int c = nextChar();
    while (isSpace(c))
      c = nextChar();
    if (isLetter(c))
      return new Token(LETTER, (char)c, pos);
    switch (c) {
    case '|':  return new Token(ALTER, (char)c, pos);
    case '*':  return new Token(REPEAT, (char)c, pos);
    case '(':  return new Token(LPAREN, (char)c, pos);
    case ')':  return new Token(RPAREN, (char)c, pos);
    case '\n': return new Token(END, (char)c, pos);
    }      
    throw new Exception("(" + pos + ") Lexical Error: Illegal character " + 
			((c==-1)? "EOF" : (char)c));
  }

}
