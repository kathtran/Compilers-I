// This is supporting software for CS321/CS322 Compilers and Language Design.
// Copyright (c) Portland State University.
//---------------------------------------------------------------------------
// For CS321 F'15 (J. Li).
//

// A lexer for operators -- handling common prefix.
//
// Tokens:
//   <LTTR:  ['a'-'z']>     
//   <ADD:   "+">
//   <SUB:   "-">
//   <ASSGN: "=">
//   <EQ:    "==">
//   <LT:    '<'>
//   <LE:    "<=">
//   <RT:    '>'>
//   <RE:    ">=">
//   <END:   '\n'>
//   <SL:    "<<">
//   <SLE:   "<<=">
//   <SR:    ">>">
//   <SRE:   ">>=">
//
import java.io.*;

public class Lexer1 {
  private static FileReader input = null;
  private static int pos = 0;	// currect lexing position
  private static int nextC = -1;  // buffer for holding next char	

  // Internal token code
  enum TokenCode { LTTR, ADD, SUB, ASSGN, EQ, LT, LE, RT, RE, END, SL, SLE, SR, SRE; }

  // Token representation
  //
  static class Token {
    TokenCode code;   // code
    String lex;       // lexeme
    int pos;          // position
  
    public Token(TokenCode code, String lex, int pos) {
      this.code = code; this.lex = lex; this.pos = pos;  
    }
    public String toString() {
      return String.format("(%02d) [%s]\t%s", pos, code, (lex=="\n"? "\\n" : lex));
    }
  }

  // Utility routines
  //
  static void init(FileReader in) throws Exception { 
    input = in; 
    nextC = input.read();
  }

  private static boolean isSpace(int c) {
    return (c == ' ' || c == '\t' || c == '\r');
  }

  private static boolean isLetter(int c) {
    return ('a' <= c && c <= 'z');
  }

  // Return next char
  //
  private static int nextChar() throws Exception {
    int c = nextC;
    nextC = input.read();
    if (c != -1)
      pos++;
    return c;
  }

  // Return next token (the main lexer routine)
  //
  static Token nextToken() throws Exception {
    int c = nextChar();
    // skip whitespace
    while (isSpace(c))
      c = nextChar();
    // recognize letter chars
    if (isLetter(c)) {
      return new Token(TokenCode.LTTR, "" + (char)c, pos);
    } 
    // recognize operators and delimiters
    switch (c) {
    case '\n': 
      return new Token(TokenCode.END, "\n", pos);
    case '+': 
      return new Token(TokenCode.ADD, "+", pos);
    case '-': 
      return new Token(TokenCode.SUB, "-", pos);
    case '=':  
      if (nextC == '=') {
	c = nextChar();
	return new Token(TokenCode.EQ, "==", pos);
      }
      return new Token(TokenCode.ASSGN, "=", pos);
    case '<':  
      if (nextC == '=') {
	c = nextChar();
	return new Token(TokenCode.LE, "<=", pos);
      }
      //*** MY CODE ***//
      if (nextC == '<') {
        c = nextChar();
        if (nextC == '=') {
          c = nextChar();
          return new Token(TokenCode.SLE, "<<=", pos);
        }
        return new Token(TokenCode.SL, "<<", pos);
      }
      //*** END MY CODE ***//
      return new Token(TokenCode.LT, "<", pos);
    case '>':  
      if (nextC == '=') {
	c = nextChar();
	return new Token(TokenCode.RE, ">=", pos);
      }
      //*** MY CODE ***//
      if (nextC == '>') {
        c = nextChar();
        if (nextC == '=') {
          c = nextChar();
          return new Token(TokenCode.SRE, ">>=", pos);
        }
        return new Token(TokenCode.SR, ">>", pos);
      }
      //*** END MY CODE ***//
      return new Token(TokenCode.RT, ">", pos);
    }      
    throw new Exception("Lexical Error at column " + pos + 
			": Illegal character " + (char)c);
  }

}
