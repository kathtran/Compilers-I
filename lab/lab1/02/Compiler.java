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
      Parser.parse(input);
      input.close();
      System.err.println("Parsing successful");
    } 
    catch (Exception e) {
      System.err.println(e.getMessage());
    }
  } 
}
