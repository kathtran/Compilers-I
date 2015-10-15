//----------------------------------------------------------------------
// A starter version of miniJava lexer (manual version). (For CS321 HW1)
//----------------------------------------------------------------------
//
// Include your name here.
//
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
    ADD, SUB, MUL, DIV, AND, OR, NOT, EQ, NE, LT, LE, GT, GE,  ASSGN,
    SEMI, COMMA, DOT, LPAREN, RPAREN, LBRAC, RBRAC, LCURLY, RCURLY;
  }

  // Token representation
  //
  static class Token {
    TokenCode code;
    String lexeme;
    int line;	   	// line # of token's first char
    int column;    	// column # of token's first char
    
    public Token(TokenCode code, String lexeme, int line, int column) {
      this.code=code; this.lexeme=lexeme;
      this.line=line; this.column=column; 
    }

    public String toString() {
      return String.format("(%d,%2d) %-10s %s", line, column, code, 
			   (code==TokenCode.STRLIT)? "\""+lexeme+"\"" : lexeme);
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


    // ... add code ...


  }

  // Return next token (the main lexer routine)
  //
  // - need to capture the line and column numbers of the first char 
  //   of each token
  //
  static Token nextToken() throws Exception {


    // ... add code ...

  }

}
