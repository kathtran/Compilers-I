// This is supporting software for CS321/CS322 Compilers and Language Design.
// Copyright (c) Portland State University
//
// (For CS321 Lab7, A. Tolmach)
//

abstract class T {
  abstract int sum();
}

class T0 extends T {
  int sum() {
    return 0;
  }
}

class T1 extends T {
  private int x;
  private T left;
  private T right;
  T1 (int x, T left, T right) { 
      this.x = x; this.left = left; this.right = right; }

  int sum() {
    return x + left.sum() + right.sum();
  }
}

class Example {
  public static void main (String argv[]) {
    T t = new T1 (1, new T1(2, new T0(), new T0()),
                     new T1(4, new T0(), new T1(3, new T0(), new T0())));
    System.out.println ("sum = " + t.sum());
  }
}
