// This is supporting software for CS321/CS322 Compilers and Language Design.
// Copyright (c) Portland State University.
//---------------------------------------------------------------------------
// For CS321 F'15 (J. Li).
//

// A FULL-VERSION parser for the RE language.
//  - It generates an interal AST for the input.
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

  private static void verboseMsg(String name, Ast.Expr node, int pos) {
    if (verbose) {
      System.out.printf("(%02d) ", pos);
      for (int i=0; i<indent; i++)
	System.out.printf(" ");
      System.out.printf("Created a %s node %s\n", name, node);
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
  static Ast.Expr parse(FileReader input) throws Exception {
    Lexer.init(input);		    // pass input handle to lexer
    nextToken = Lexer.nextToken();  // get first token ready
    return parseExpr();
  }

  // Expr -> Alter '\n'
  //
  private static Ast.Expr parseExpr() throws Exception {
    Ast.Expr expr = parseAlter();
    match(Lexer.END);
    return expr;
  }

  // Alter -> Concat [ '|' Alter ]
  //
  private static Ast.Expr parseAlter() throws Exception {
    Ast.Expr rand1 = parseConcat();
    if (nextToken.code == Lexer.ALTER) {
      match(Lexer.ALTER);
      if (verbose) indent++;
      Ast.Expr rand2 = parseAlter();
      if (verbose) indent--;
      Ast.Expr alter = new Ast.Alter(rand1, rand2);
      verboseMsg("Alter", alter, nextToken.pos);
      return alter;
    }
    return rand1;
  }

  // Concat -> Repeat [ Concat ]
  //
  private static Ast.Expr parseConcat() throws Exception {
    Ast.Expr rand1 = parseRepeat();
    if (nextToken.code == Lexer.LETTER || nextToken.code == Lexer.LPAREN) {
      if (verbose) indent++;
      Ast.Expr rand2 = parseConcat();
      if (verbose) indent--;
      Ast.Expr concat = new Ast.Concat(rand1, rand2);
      verboseMsg("Concat", concat, nextToken.pos);
      return concat;
    }
    return rand1;
  }

  // Repeat -> Atom [ '*' ]
  //
  private static Ast.Expr parseRepeat() throws Exception {
    Ast.Expr rand = parseAtom();
    if (nextToken.code == Lexer.REPEAT) {
      match(Lexer.REPEAT);
      Ast.Expr repeat = new Ast.Repeat(rand);
      verboseMsg("Repeat", repeat, nextToken.pos);
      return repeat;
    } 
    return rand;
  }

  // Atom -> '(' Alter ')' | <Letter>
  //
  private static Ast.Expr parseAtom() throws Exception {
    if (nextToken.code == Lexer.LPAREN) {
      match(Lexer.LPAREN);
      Ast.Expr rand = parseAlter();
      match(Lexer.RPAREN);
      return rand;
    } else if (nextToken.code == Lexer.LETTER) {
      Ast.Expr letter = new Ast.Letter(nextToken.lex);
      verboseMsg("Letter", letter, nextToken.pos);
      match(Lexer.LETTER);
      return letter;
    }
    throw new Exception("Syntax Error at column " + nextToken.pos +
			": Expected a letter or (, got " + nextToken.lex);
  }

}
