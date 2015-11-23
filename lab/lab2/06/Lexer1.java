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

public class Lexer1 {
  private static FileReader input = null;
  private static int pos = 0;	// currect lexing position

  // Internal token code
  enum TokenCode { LETTER, ALTER, REPEAT, LPAREN, RPAREN, END; }

  // Token representation
  //
  static class Token {
    TokenCode code;   // code
    char lex; 	      // lexeme
    int pos;          // position
  
    public Token(TokenCode code, char lex, int pos) {
      this.code = code; this.lex = lex; this.pos = pos;  
    }
    public String toString() {
      return String.format("(%02d) [%s]\t%s", pos, code, (lex=='\n'? "\\n" : lex));
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

  // Return next token (the main lexer routine)
  //
  static Token nextToken() throws Exception {
    int c = nextChar();
    while (isSpace(c))
      c = nextChar();
    if (isLetter(c))
      return new Token(TokenCode.LETTER, (char)c, pos);
    switch (c) {
    case '|':  return new Token(TokenCode.ALTER, (char)c, pos);
    case '*':  return new Token(TokenCode.REPEAT, (char)c, pos);
    case '(':  return new Token(TokenCode.LPAREN, (char)c, pos);
    case ')':  return new Token(TokenCode.RPAREN, (char)c, pos);
    case '\n': return new Token(TokenCode.END, (char)c, pos);
    }      
    throw new Exception("Lexical Error at column " + pos + 
			": Illegal character " + ((c==-1)? "EOF" : (char)c));
  }

}
