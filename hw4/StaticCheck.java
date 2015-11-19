// This is supporting software for CS321/CS322 Compilers and Language Design.
// Copyright (c) Portland State University.
//---------------------------------------------------------------------------
// For CS321 F'15 (J. Li).
//

// Driver for testing two static checks: 
//  (1) statement reachability, 
//  (2) variable initionaization.
//
import java.io.*;

class StaticCheck {
  public static void main(String [] args) {
    if (args.length < 1) {
      System.out.println("Need an AST file name as command-line argument.");
      return;
    } 
    try {
      FileReader input = new FileReader(args[0]);
      Ast.Program p = new AstParser(input).Program();
      input.close();
      p.checkReach();		// detecting unreachable statements
      p.checkVarInit(); 	// detecting uninitialized variables
    } catch (StaticError e) {
      System.err.print(e);
    } catch (Exception e) {
      System.err.print(e);
    }     
  }
}
