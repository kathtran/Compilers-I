// This is supporting software for CS321/CS322 Compilers and Language Design.
// Copyright (c) Portland State University
//---------------------------------------------------------------------------
// For CS321 F'15 (Tolmach & Li).
//

import java.util.*;

// A class for immutable environments (mapping from Strings to Objects).
//---------------------------------------------------------------------------
// 
class Env extends HashMap<String,Object> {
  Env extend(String s, Object v) {
    Env e = new Env();
    e.putAll(this);
    e.put(s,v);
    return e;
  }
}
//---------------------------------------------------------------------------

class RuntimeError extends Exception {
  RuntimeError(String message) {
    super(message);
  }
}

abstract class Exp {
  abstract Object eval(Env env) throws RuntimeError;
}

class LetExp extends Exp {
  String x;
  Exp d;
  Exp e;
  LetExp(String x, Exp d, Exp e) {this.x = x; this.d = d; this.e = e;}

  Object eval(Env env) throws RuntimeError {
    Object v = d.eval(env);
    return e.eval(env.extend(x,v));
  }
}

class VarExp extends Exp {
  String x;
  VarExp(String x) {this.x = x;}
  
  Object eval(Env env) throws RuntimeError {
    Object v = env.get(x);
    if (v != null)
      return v;
    else
      throw new RuntimeError("undefined variable '" + x + "'"); 
  }
}

class NumExp extends Exp {
  int num;
  NumExp(int num) {this.num = num;}

  Object eval(Env env) {
    return num;
  }
}

class AddExp extends Exp {
  Exp left;
  Exp right;
  AddExp (Exp left, Exp right) {this.left = left; this.right = right;}

  Object eval(Env env) throws RuntimeError {
    try {
      return (Integer) left.eval(env) + (Integer) right.eval(env);
    } catch (ClassCastException exn) {
      throw new RuntimeError("Add requires INT operands");
    }
  }
}

class SubExp extends Exp {
  Exp left;
  Exp right;
  SubExp (Exp left, Exp right) {this.left = left; this.right = right;}

  Object eval(Env env) throws RuntimeError {
    try {
      return (Integer) left.eval(env) - (Integer) right.eval(env);
    } catch (ClassCastException exn) {
      throw new RuntimeError("Sub requires INT operands");
    }
  }
}

class AndExp extends Exp {
  Exp left;
  Exp right;
  AndExp (Exp left, Exp right) {this.left = left; this.right = right;}

  Object eval(Env env) throws RuntimeError {
    try {
      return (Boolean) left.eval(env) && (Boolean) right.eval(env);
    } catch (ClassCastException exn) {
      throw new RuntimeError("And requires BOOL operands");
    }
  }
}

class NotExp extends Exp {
  Exp e;
  NotExp (Exp e) {this.e = e;}

  Object eval(Env env) throws RuntimeError {
    try {
      return ! (Boolean) e.eval(env);
    } catch (ClassCastException exn) {
      throw new RuntimeError("Not requires BOOL operand");
    }
  }
}

class IfExp extends Exp {
  Exp test;
  Exp t;
  Exp f;
  IfExp (Exp test, Exp t, Exp f) {this.test = test; this.t = t; this.f = f;}
  Object eval(Env env) throws RuntimeError {
    boolean b;
    try {
      b = (Boolean) test.eval(env);
    } catch (ClassCastException exn) {
      throw new RuntimeError("If requires BOOL first operand");
    }
    if (b)
      return t.eval(env);
    else
      return f.eval(env);
  }
}

class LeqExp extends Exp {
	Exp left;
	Exp right;
	LeqExp(Exp left, Exp right) {this.left = left; this.right = right;}
}

class Example {
  public static void main(String argv[]) {
    Exp e = new LetExp("y",
                       new NotExp(new AndExp (new VarExp("true"),
                                              new VarExp("false"))),
                       new IfExp(new VarExp("y"),
                                 new LetExp ("y",
                                             new NumExp(21),
                                             new AddExp(new VarExp("y"),
                                                        new VarExp("y"))),
                                 new VarExp("y")));
    Env venv = new Env().extend("true",true).extend("false",false);
    try {
      System.out.println("value = " + e.eval(venv));
    } catch (RuntimeError exn) {
      System.err.println(exn.toString());
    }
  }
}



    
