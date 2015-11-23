// This is supporting software for CS321/CS322 Compilers and Language Design.
// Copyright (c) Portland State University
//---------------------------------------------------------------------------
// For CS321 F'15 (Tolmach & Li).
//

import java.util.*;

// A class for immutable environments (mapping from Strings to Integers).
//---------------------------------------------------------------------------
// Operations:
//  get(x)      -- return the value of x
//  extend(x,v) -- return a new env with a new binding (x,v) added
// 
class Env extends HashMap<String,Integer> {
  Env extend(String s, Integer v) {
    Env e = new Env();
    e.putAll(this);
    e.put(s,v);
    return e;
  }
}
//---------------------------------------------------------------------------

abstract class Exp {
  abstract int eval(Env env);
}

class LetExp extends Exp {
  private String x;
  private Exp d;
  private Exp e;
  LetExp(String x, Exp d, Exp e) {this.x = x; this.d = d; this.e = e;}

  int eval(Env env) {
    int v = d.eval(env);
    return e.eval(env.extend(x,v));
  }
}

class VarExp extends Exp {
  private String x;
  VarExp(String x) {this.x = x;}
  
  int eval(Env env) {
    Integer v = env.get(x);
    if (v != null)
      return v;
    else
      return 0;  // default value for undefined variables
  }
}

class NumExp extends Exp {
  private int num;
  NumExp(int num) {this.num = num;}

  int eval(Env env) {
    return num;
  }
}

class AddExp extends Exp {
  private Exp left;
  private Exp right;
  AddExp (Exp left, Exp right) {this.left = left; this.right = right;}

  int eval(Env env) {
    return left.eval(env) + right.eval(env);
  }
}

class SubExp extends Exp {
  private Exp left;
  private Exp right;
  SubExp (Exp left, Exp right) {this.left = left; this.right = right;}

  int eval(Env env) {
    return left.eval(env) - right.eval(env);
  }
}

class IfnzExp extends Exp {
  private Exp test;
  private Exp nz;
  private Exp z;
  IfnzExp (Exp test, Exp nz, Exp z) {this.test = test; this.nz = nz; this.z = z;}
  int eval(Env env) {
    if (test.eval(env) == 0)
      return z.eval(env);
    else
      return nz.eval(env);
  }
}

class Example {
  public static void main(String argv[]) {
    Exp e = new LetExp("y",
                       new SubExp(new NumExp(5),
                                  new AddExp(new NumExp(2),
                                             new NumExp(3))),
                       new IfnzExp(new VarExp("y"),
                                   new NumExp(10),
                                   new LetExp ("y",
                                               new NumExp(21),
                                               new AddExp(new VarExp("y"),
                                                          new VarExp("y")))));
    System.out.println("value = " + e.eval(new Env()));

		Exp f = new LetExp("x",
											 new LetExp("x",new NumExp(2),
																	new AddExp(new VarExp("x"), new VarExp("x"))),
											 new IfnzExp(new VarExp("x"), new NumExp(20), new NumExp(30)));
		System.out.println("value = " + f.eval(new Env()));
  }
}



    
