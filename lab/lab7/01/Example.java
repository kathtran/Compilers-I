// This is supporting software for CS321/CS322 Compilers and Language Design.
// Copyright (c) Portland State University
//
// (For CS321 Lab7, A. Tolmach)
//

class T {
  private int x;
  private T left;
  private T right;
  T (int x, T left, T right) {
    this.x = x; this.left = left; this.right = right;
  }

  int sum () {
    return x + (left != null ? left.sum() : 0) + 
               (right != null ? right.sum() : 0);
  }

	int size () {
		return (left != null || right != null ? 1 : 0) +
					 (left != null ? left.size() : 0) +
					 (right != null ? right.size() : 0);
	}
}

class Example {
  public static void main (String argv[]) {
    T t = new T (1, new T(2, null, null), 
                    new T(4, null, new T(3, null, null)));
    System.out.println ("sum = " + t.sum());
		System.out.println ("size = " + t.size());
  }
}
