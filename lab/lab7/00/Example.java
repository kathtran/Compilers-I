// This is supporting software for CS321/CS322 Compilers and Language Design.
// Copyright (c) Portland State University
//
// (For CS321 Lab7, A. Tolmach)
//

class T {
  int x;       // node value
  T left;      // child
  T right;     // child
  T (int x, T left, T right) {
    this.x = x; this.left = left; this.right = right;
  }
}

class Example {
  public static void main (String argv[]) {
    T t = new T (1, new T(2, null, null), 
                    new T(4, null, new T(3, null, null)));
    System.out.println ("sum = " + sum(t));
  }

  static int sum (T t) {
    if (t != null)
      return t.x + sum(t.left) + sum(t.right);
    else
      return 0;
  }
}
    