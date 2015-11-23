// This is supporting software for CS321/CS322 Compilers and Language Design.
// Copyright (c) Portland State University.
//---------------------------------------------------------------------------
// For CS321 F'15 (J. Li).
//

// A BASE-VERSION parser for the RE language.
//  - It validates the syntax of input, but does not generate output.
//
// RE Grammar:
//   Expr   -> Alter '\n'
//   Alter  -> Concat [ '|' Alter ]
//   Concat -> Repeat [ Concat ]
//   Repeat -> Atom [ '*' ]
//   Atom   -> '(' Alter ')' | <Letter>
// 
//
import java.io.*;

public class Parser {
  private static Lexer.Token nextToken;     // next token from input
  private static boolean verbose = false;
  private static int indent = 0;

  static void verboseOn() { verbose = true; }

  private static void verboseMsg(String name, int pos) {
    if (verbose) {
      System.out.printf("(%02d) ", pos);
      for (int i=0; i<indent; i++)
	System.out.printf(" ");
      System.out.printf("Parsed a %s\n", name);
    }
  }

  // Check that 'nextToken' matches the expected token
  //
  static void match(int expected) throws Exception {
    if (nextToken.code == expected) {
      if (nextToken.code != Lexer.END)
	nextToken = Lexer.nextToken();
    } else {
      throw new Exception("Syntax Error at column " + nextToken.pos +
			  ": Expected " + expected + ", got " +
			  nextToken.code + "\n");
    }
  }    

  // The main parser routine
  //
  static void parse(FileReader input) throws Exception {
    Lexer.init(input);              // pass input handle to lexer
    nextToken = Lexer.nextToken();  // get first token ready
    parseExpr();
  }

  // Expr -> Alter '\n'
  //
  static void parseExpr() throws Exception {
    parseAlter();
    match(Lexer.END);
    verboseMsg("End", nextToken.pos);
  }

  // Alter -> Concat [ '|' Alter ]
  //
  static void parseAlter() throws Exception {
    parseConcat();
    if (nextToken.code == Lexer.ALTER) {
      match(Lexer.ALTER);
      if (verbose) indent++;
      parseAlter();
      if (verbose) indent--;
      verboseMsg("Alter", nextToken.pos);
    }
  }

  // Concat -> Repeat [ Concat ]
  //
  static void parseConcat() throws Exception {
    parseRepeat();
    if (nextToken.code == Lexer.LETTER || nextToken.code == Lexer.LPAREN) {
      if (verbose) indent++;
      parseConcat();
      if (verbose) indent--;
      verboseMsg("Concat", nextToken.pos);
    }
  }

  // Repeat -> Atom [ '*' ]
  //
  static void parseRepeat() throws Exception {
    parseAtom();
    if (nextToken.code == Lexer.REPEAT) {
      match(Lexer.REPEAT);
      verboseMsg("Repeat", nextToken.pos);
    }
  }

  // Atom -> '(' Alter ')' | <Letter>
  //
  static void parseAtom() throws Exception {
    if (nextToken.code == Lexer.LPAREN) {
      match(Lexer.LPAREN);
      parseAlter();
      match(Lexer.RPAREN);
    } else if (nextToken.code == Lexer.LETTER) {
      verboseMsg("Letter " + nextToken.lex, nextToken.pos);
      match(Lexer.LETTER);
    } else {
      throw new Exception("Syntax Error at column " + nextToken.pos +
			  ": Expected a letter or (, got " + nextToken.lex);
    }
  }  

}
