// This is supporting software for CS321/CS322 Compilers and Language Design.
// Copyright (c) Portland State University
//
// (For CS321 Lab7, A. Tolmach)
//

import java.util.*;

abstract class T {
  static Set<Integer> s;
  abstract void addToVals();
  static Set<Integer> vals(T t) {
    s = new HashSet<Integer>();
    t.addToVals();
    return s;
  }
}

class T0 extends T {
  private int x;
  T0 (int x) { this.x = x; }
  
  void addToVals() {
    s.add(x);
  }
}

class T1 extends T {
  private int x;
  private T left;
  private T right;
  T1 (int x, T left, T right) { 
    this.x = x; this.left = left; this.right = right; }

  void addToVals() {
    s.add(x);
    left.addToVals();
    right.addToVals();
  }
}

class Example {
  public static void main (String argv[]) {
    T t = new T1(1, new T0(2), new T1(3, new T0(4), new T0(3)));
    System.out.println ("distinct elements = " + T.vals(t).size());
  }

}
