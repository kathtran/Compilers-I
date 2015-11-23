// This is supporting software for CS321/CS322 Compilers and Language Design.
// Copyright (c) Portland State University.
//---------------------------------------------------------------------------
// For CS321 F'15 (J. Li).
//

// AST Generation for "Hello.java".
//
// miniJava:
//   class Hello {
//     public static void main(String[] a) {
//       System.out.println("Hello World!");
//     }
//   } 
//
// AST Program (external dump form):
//   ClassDecl Hello 
//    MethodDecl void main ()
//     Print "Hello World!"
//
//
import java.util.*;
import java.io.*;
import ast.*;

class HelloAst {
  public static void main(String [] args) {
    // Prepare arguments for constructing a MethodDecl
    List<Ast.Param> pl = new ArrayList<Ast.Param>();     // empty param list
    List<Ast.VarDecl> vl = new ArrayList<Ast.VarDecl>(); // empty var list
    List<Ast.Stmt> sl = new ArrayList<Ast.Stmt>();       // empty stmt list

    // Construct a print stmt and add it to stmt list
    Ast.Exp arg = new Ast.StrLit("Hello World!");       // create an arg
    Ast.Stmt s = new Ast.Print(arg);                    // create a print stmt
    sl.add(s);                                          // add stmt to list
    
    // Construct a MethodDecl ---
    //   Ast.MethodDecl(Ast.Type rt, String m, List<Ast.Param> pl, 
    //                  List<Ast.VarDecl> vl, List<Ast.Stmt> sl)
    Ast.MethodDecl md = new Ast.MethodDecl(null,       	// null represents 'void'
					   "main",	// method's name
					   pl, 		// formal params
					   vl, 		// local var decls
					   sl); 	// method body
    List<Ast.MethodDecl> ml = new ArrayList<Ast.MethodDecl>();
    ml.add(md);

    // Construct a ClassDecl ---
    //   Ast.ClassDecl(String nm, String pnm, 
    //                 List<Ast.VarDecl> vl, List<Ast.MethodDecl> ml)
    Ast.ClassDecl cd = new Ast.ClassDecl("Hello",	// class name
					 null, 		// parent's name
					 vl, 		// field decls 
					 ml); 		// method decls
    List<Ast.ClassDecl> cl = new ArrayList<Ast.ClassDecl>();
    cl.add(cd);

    // Construct a Program ---
    //   Ast.Program(List<Ast.ClassDecl> cl)
    Ast.Program p = new Ast.Program(cl); 	// create the whole AST
    System.out.print(p); 			// dump out the AST
  }
}
