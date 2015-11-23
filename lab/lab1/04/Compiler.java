// This is supporting software for CS321/CS322 Compilers and Language Design.
// Copyright (c) Portland State University.
//---------------------------------------------------------------------------
// For CS321 F'15 (J. Li).
//

// A compiler driver.
//
import java.io.*;

public class Compiler {

  // The main parser routine
  // - open input and output files
  // - parse content to an AST
  // - close input file
  // - dump AST to output file (and to standard out)
  //
  public static void main(String[] args) {
    String fname;
    if (args.length == 1) {
      fname = args[0];
    } else if (args.length == 2 && args[0].startsWith("-v", 0)) {
      Parser.verboseOn();
      fname = args[1];
    } else {
      System.out.println("Need a file name as command-line argument.");
      return;
    } 
    try {
      FileReader input = new FileReader(fname);
      FileWriter output = new FileWriter(fname + ".dot");

      // start parsing
      Ast.Expr expr = Parser.parse(input);	
      input.close();

      // dump the AST in two forms
      output.write(expr.dotForm());	// dump AST to output file
      output.close();	
      System.out.println(expr);		// print AST for viewing

      // semantic check
      if (!expr.vowelCheck())
	throw new Exception("Vowel check failed");
      System.out.println("Semantic check successful (one or more vowels are found)");	  

      // "evaluate" the RE
      System.out.println("Total letters in RE: " + expr.letterCount());	  
    } 
    catch (Exception e) {
      System.err.println(e.getMessage());
    }
  } 
}
