// This is supporting software for CS321/CS322 Compilers and Language Design.
// Copyright (c) Portland State University.
//---------------------------------------------------------------------------
// For CS321 F'15 (J. Li).
//

// AST Generation for "Sum.java"
//
// miniJava:
//   class Sum {
//     public static void main(String[] ignore) {
//       int x = 1;
//       int y = 2;
//       int z = 3;
//       int sum;
//       sum = x + y + z;
//       System.out.println(sum);
//     }
//   }    
//
import java.util.*;
import java.io.*;
import ast.*;

class SumAst {
  public static void main(String [] args) {

    List<Ast.Param> pl = new ArrayList<Ast.Param>();	

    // Ast.VarDecl(Type t, String nm, Exp init)
    Ast.IntType intT = new Ast.IntType();
    Ast.VarDecl v1 = new Ast.VarDecl(intT, "x", new Ast.IntLit(1));
    Ast.VarDecl v2 = new Ast.VarDecl(intT, "y", new Ast.IntLit(2));
    Ast.VarDecl v3 = new Ast.VarDecl(intT, "z", new Ast.IntLit(3));
    Ast.VarDecl v4 = new Ast.VarDecl(intT, "sum", null);

    List<Ast.VarDecl> vl = new ArrayList<Ast.VarDecl>(); // empty var list
    vl.add(v1);
    vl.add(v2);
    vl.add(v3);
    vl.add(v4);

    // Ast.Assign(Exp lhs, Exp rhs)
    // Ast.Binop(BOP, Exp lhs, Exp rhs)
    Ast.Stmt s1 = new Ast.Assign(new Ast.Id("sum"),
				 new Ast.Binop(Ast.BOP.ADD, 
					       new Ast.Binop(Ast.BOP.ADD, 
							     new Ast.Id("x"),
							     new Ast.Id("y")),
					       new Ast.Id("z")));
    // Ast.Print(Ast.Exp arg)
    Ast.Stmt s2 = new Ast.Print(new Ast.Id("sum"));

    List<Ast.Stmt> sl = new ArrayList<Ast.Stmt>();
    sl.add(s1);
    sl.add(s2);

    // Ast.MethodDecl(Ast.Type rt, String m, List<Ast.Param> pl, 
    //                List<Ast.VarDecl> vl, List<Ast.Stmt> sl)
    Ast.MethodDecl md = new Ast.MethodDecl(null, "main", pl, vl, sl);     
    List<Ast.MethodDecl> ml = new ArrayList<Ast.MethodDecl>();
    ml.add(md);

    vl = new ArrayList<Ast.VarDecl>(); 

    // Ast.ClassDecl(String nm, String pnm, 
    //               List<Ast.VarDecl> vl, List<Ast.MethodDecl> ml)
    Ast.ClassDecl cd = new Ast.ClassDecl("Sum", null, vl, ml);
    List<Ast.ClassDecl> cl = new ArrayList<Ast.ClassDecl>();
    cl.add(cd);

    // Ast.Program(List<Ast.ClassDecl> cl)
    Ast.Program p = new Ast.Program(cl);
    System.out.print(p);
  }
}
