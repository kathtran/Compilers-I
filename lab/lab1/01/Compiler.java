// This is supporting software for CS321/CS322 Compilers and Language Design.
// Copyright (c) Portland State University.
//---------------------------------------------------------------------------
// For CS321 F'15 (J. Li).
//

// A compiler driver.
//
import java.io.*;

public class Compiler {

  // The main routine
  // - open input file, parse content, then close the file
  //
  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Need a file name as command-line argument.");
      return;
    } 
    try {
      FileReader input = new FileReader(args[0]);
      Lexer.init(input);
      Lexer.Token tkn;
      do {
	  tkn = Lexer.nextToken();
	  System.out.println(tkn);
      } while (tkn.code != Lexer.END);
      input.close();
    } 
    catch (Exception e) {
      System.err.println(e.getMessage());
    }
  } 
}
