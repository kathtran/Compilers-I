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
    T t = new T2 (1, 4, new T1(2, new T0(), new T0()), new T2(4, 5, new T0(), new T0(), new T0()),
                     new T1(4, new T0(), new T1(3, new T0(), new T0())));
    System.out.println ("sum = " + t.sum());
  }
}

class T2 extends T1 {
	private int y;
	private T center;
	T2 (int x, int y, T left, T center, T right) {
		super (x, left, right);
		this.y = y; this.center = center;
	}

	int sum() {
		return y + center.sum() + super.sum();
	}
}
