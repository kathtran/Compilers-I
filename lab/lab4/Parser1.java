// This is supporting software for CS321/CS322 Compilers and Language Design.
// Copyright (c) Portland State University.
//---------------------------------------------------------------------------
// For CS321 F'15 (J. Li).
//

// An LL(1) parser for
//
// Grammar1:
//  0. Program0 -> Program $
//  1. Program  -> begin StmtList end
//  2. StmtList -> Stmt ; StmtList
//  3. StmtList -> 
//  4. Stmt     -> simple
//  5. Stmt     -> begin StmtList end
//

import java.util.Scanner;
import java.io.*;

class Parser1 {

  enum TokenCode { BEGIN, END, SIMPLE, SEMICOL, EOF; }

  // Lexer 
  // - use Java's Scanner for lexing
  // - assume adjacent tokens are separated by white space
  // - only token code is returned; lexeme is not kept
  //
  static Scanner scanner;	
  static TokenCode tknCode;

  // Read from input and return next token's code
  //
  static TokenCode nextToken() throws Exception {
    String lexeme = null;
    if (scanner.hasNext()) {
      lexeme = scanner.next();
      if (lexeme.equals("begin")) 
	return TokenCode.BEGIN;
      if (lexeme.equals("end")) 
	return TokenCode.END;
      if (lexeme.equals("simple")) 
	return TokenCode.SIMPLE;
      else if (lexeme.equals(";"))
	return TokenCode.SEMICOL;
      throw new Exception("Illegal token: " + lexeme);
    }
    return TokenCode.EOF;
  }

  // Match a token and move input pointer to the next token
  //
  static void match(TokenCode code) throws Exception {
    if (tknCode == code)
      tknCode = nextToken();
    else
      throw new Exception("Token mismatch: expected " + code + " got " + tknCode); 
  }

  // 0. Program0 -> Program $ 
  // (The main method implements the augmented production.)
  //
  public static void main(String [] args) throws Exception {
    scanner = new Scanner(System.in);
    tknCode = nextToken();
    Program(); 
    match(TokenCode.EOF);
    System.out.println("Syntax verified.");
  }

  //  ... add remaining parsing routines here ...
  static void Program() throws Exception {
//    if (tknCode == TokenCode.BEGIN) {
      match(TokenCode.BEGIN);
      StmtList();
      match(TokenCode.END);
//    }
  }

  static void Stmt() throws Exception {
    if (tknCode == TokenCode.SIMPLE) {
      match(TokenCode.SIMPLE);
    }
    else if (tknCode == TokenCode.BEGIN) {
      match(TokenCode.BEGIN);
      StmtList();
      match(TokenCode.END);
    }
  }

  static void StmtList() throws Exception {
/*    if (tknCode == TokenCode.SIMPLE || tknCode == TokenCode.BEGIN) {
      Stmt();
      StmtList();
    }
*/
    Stmt();
    if (tknCode == TokenCode.SEMICOL) {
      match(TokenCode.SEMICOL);
      StmtList();
    }
    if (tknCode == TokenCode.END)
      return;
  }
}


