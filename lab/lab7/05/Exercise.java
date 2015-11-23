// This is supporting software for CS321/CS322 Compilers and Language Design.
// Copyright (c) Portland State University
//
// (For CS321 Lab7, Tolmach & Li)
//

import java.util.*;

// Define a class for immutable sets by extending Java's (mutable) HashSet class.
//-------------------------------------------------------------------------------
// For every operation of union, intersect, and add, a new set is created to
// hold the result.
//
class ImmIntSet extends HashSet<Integer> {
  public static ImmIntSet union(ImmIntSet s1, ImmIntSet s2) {
    ImmIntSet s = new ImmIntSet();
    s.addAll(s1);
    s.addAll(s2);
    return s;
  }

  public static ImmIntSet intersect(ImmIntSet s1, ImmIntSet s2) {
    ImmIntSet s = new ImmIntSet();
    for (int x : s1)
      if (s2.contains(x))
	s.add(x);
    return s;
  }

  public static ImmIntSet add(ImmIntSet s0, int x) { 
    ImmIntSet s = new ImmIntSet();
    s.addAll(s0);
    s.add(x);
    return s;
  }
}
//-------------------------------------------------------------------------------

abstract class T {
  boolean isDup = false;
  abstract void setDups (ImmIntSet s);
  abstract void print(int n);
  static void indent(int n, String m) {
    for (int i=0; i<n; i++) 
      System.out.print(" ");
    System.out.println(m);
  }
}

class T0 extends T {
  void print(int n) {
  }

	void setDups (ImmIntSet s) {
	}
}

class T1 extends T {
  int x;
  T left;
  T right;
  T1 (int x, T left, T right) { 
    this.x = x; this.left = left; this.right = right; }

  void print(int n) {
    indent(n, "" + x + " " + isDup);
    left.print(n+1);
    right.print(n+1);
  }

	void setDups (ImmIntSet s) {
		if (s != null && s.contains(x))
			isDup = true;
		s.add(x);
		if (left != null)
			left.setDups(s);
		if (right != null)
			right.setDups(s);
	}
}

class Exercise {
  public static void main (String argv[]) {
    T t = new T1(1, new T1(3, new T1(4, new T0(), new T0()),
			      new T1(3, new T0(), new T0())),
                    new T1(1, new T0(), new T0()));
    t.setDups(new ImmIntSet());
    t.print(0);
  }

}    
