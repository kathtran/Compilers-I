// This is supporting software for CS321/CS322 Compilers and Language Design.
// Copyright (c) Portland State University
//---------------------------------------------------------------------------
// For CS321 F'15 (Tolmach & Li).
//

import java.util.*;

// A class for imperative environments that supports retraction of bindings
// (mapping from Strings to Integers).
//---------------------------------------------------------------------------
// 
class Env extends HashMap<String,Stack<Integer>> {
  void extend(String s, Integer v) {
    Stack<Integer> stack = this.get(s);
    if (stack == null) {
      stack = new Stack<Integer>();
      this.put(s, stack);
    }
    stack.push(v);
  }
    
  void retract(String s) {
    Stack<Integer> stack = this.get(s);  // should never be null
    stack.pop();
    if (stack.empty()) 
      this.remove(s);
  }
      
  Integer lookup(String s) {
    Stack<Integer> stack = this.get(s);
    if (stack == null)
      return null;
    return stack.peek();
  }
}
//---------------------------------------------------------------------------

abstract class Exp {
  static Env env = new Env();
  abstract int eval();
}

class LetExp extends Exp {
  private String x;
  private Exp d;
  private Exp e;
  LetExp(String x, Exp d, Exp e) {this.x = x; this.d = d; this.e = e;}

  int eval() {
    int v = d.eval();
    env.extend(x,v);
    v = e.eval();
    env.retract(x);
    return v;
  }
}

class VarExp extends Exp {
  private String x;
  VarExp(String x) {this.x = x;}
  
  int eval() {
    Integer v = env.lookup(x);
    if (v != null)
      return v;
    else
      return 0;  // default value for undefined variables
  }
}

class NumExp extends Exp {
  private int num;
  NumExp(int num) {this.num = num;}

  int eval() {
    return num;
  }
}

class AddExp extends Exp {
  private Exp left;
  private Exp right;
  AddExp (Exp left, Exp right) {this.left = left; this.right = right;}

  int eval() {
    return left.eval() + right.eval();
  }
}

class SubExp extends Exp {
  private Exp left;
  private Exp right;
  SubExp (Exp left, Exp right) {this.left = left; this.right = right;}

  int eval() {
    return left.eval() - right.eval();
  }
}

class IfnzExp extends Exp {
  Exp test;
  Exp nz;
  Exp z;
  IfnzExp (Exp test, Exp nz, Exp z) {this.test = test; this.nz = nz; this.z = z;}
  int eval() {
    if (test.eval() == 0)
      return z.eval();
    else
      return nz.eval();
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
                                   new AddExp (new LetExp ("y",
                                                           new NumExp(21),
                                                           new AddExp(new VarExp("y"),
                                                                      new VarExp("y"))),
                                               new VarExp ("y"))));

    System.out.println("value = " + e.eval());
  }
}



    
