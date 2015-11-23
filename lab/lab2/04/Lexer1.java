// This is supporting software for CS321/CS322 Compilers and Language Design.
// Copyright (c) Portland State University.
//---------------------------------------------------------------------------
// For CS321 F'15 (J. Li).
//

// A lexer for IDs and keywords.
//
// Tokens:
//   <ID:   ['a'-'z']+>
//   <IF:   "if">     
//   <ELSE: "else">     
//   <END:  '\n'>
//
import java.io.*;

public class Lexer1 {
  private static FileReader input = null;
  private static int pos = 0;	// currect lexing position
  private static int nextC = -1;  // buffer for holding next char	

  // Internal token code
  enum TokenCode { ID, IF, ELSE, END; }

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
    // recognize ID and keywords
    if (isLetter(c)) {
      StringBuilder buffer = new StringBuilder(); 
      buffer.append((char) c);
      while (isLetter(nextC)) {
	c = nextChar();
	buffer.append((char) c);
      }
      String lex = buffer.toString();
      if (lex.equals("if"))
	return new Token(TokenCode.IF, lex, pos);
      if (lex.equals("else"))
	return new Token(TokenCode.ELSE, lex, pos);
      return new Token(TokenCode.ID, lex, pos);
    } 
    // recognize <EOL>
    if (c == '\n')
      return new Token(TokenCode.END, "\n", pos);
    throw new Exception("Lexical Error at column " + pos + 
			": Illegal character " + (char)c);
  }

}
