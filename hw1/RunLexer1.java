// This is supporting software for CS321/CS322 Compilers and Language Design.
// Copyright (c) Portland State University.
//---------------------------------------------------------------------------
// For CS321 F'15 (J. Li).
//

// A driver for testing Lexer1.
//
import java.io.*;

public class RunLexer1 {

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
      Lexer1.init(input);
      Lexer1.Token tkn;
      int tknCnt = 0;
      tkn = Lexer1.nextToken();
      while (tkn != null) {
	System.out.println(tkn);
	tkn = Lexer1.nextToken();
	tknCnt++;
      }
      input.close();
      System.out.print("Total: " + tknCnt + " tokens\n");
    } catch (Exception e) {
      System.err.println(e);
    }
  } 
}
