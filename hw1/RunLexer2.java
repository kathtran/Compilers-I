// This is supporting software for CS321/CS322 Compilers and Language Design.
// Copyright (c) Portland State University.
//---------------------------------------------------------------------------
// For CS321 F'15 (J. Li).
//

// A driver for testing Lexer2.
//
import java.io.*;

public class RunLexer2 {

  // The main routine
  // - open input file
  // - repeatedly call nextToken() routine until EOF
  // - close input file
  //
  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Need a file name as command-line argument.");
      return;
    } 
    try {
      FileReader input = new FileReader(args[0]);
      Lexer2 lexer = new Lexer2(input);
      Token tkn;
      int tknCnt = 0;
      tkn = lexer.getNextToken();
      while (tkn != null && tkn.kind != 0) {
	System.out.format("(%d,%2d) %-10s %s\n", tkn.beginLine, tkn.beginColumn, 
			  Lexer2Constants.tokenImage[tkn.kind], tkn.image);
	tkn = lexer.getNextToken();
	tknCnt++;
      };
      input.close();
      System.out.print("Total: " + tknCnt + " tokens\n");
    } catch (TokenMgrError e) {
      System.err.println(e);
    } catch (Exception e) {
      System.err.println(e);
    }
  } 
}
