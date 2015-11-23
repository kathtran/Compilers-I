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
  abstract ImmIntSet vals();
  abstract boolean hasDups();    
}

class T0 extends T {
  ImmIntSet vals() {
    return new ImmIntSet();
  }
  boolean hasDups() {
    return false;
  }
}

class T1 extends T {
  private int x;
  private T left;
  private T right;
  T1 (int x, T left, T right) { 
    this.x = x; this.left = left; this.right = right; }

  ImmIntSet vals() {
    return ImmIntSet.add(ImmIntSet.union(left.vals(), right.vals()), x);
  }
  boolean hasDups() {
    return left.hasDups() || right.hasDups() || 
           left.vals().contains(x) || right.vals().contains(x);
  }
}

class Example {
  public static void main (String argv[]) {
    T t = new T1(1, new T1(2, new T0(), new T0()), 
                    new T1(3, new T1(4, new T0(), new T0()), 
                              new T1(2, new T0(), new T0())));
    System.out.println ("duplicates = "+ t.hasDups());
  }
}
    
