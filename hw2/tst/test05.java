// miniJava test program (For CS321 Language and Compiler Design, PSU)
//
// Boolean expression
//
class Test {
  public static void main(String[] ignore) {
    boolean a;
    boolean b;
    boolean c;
    int x;
    a = true;
    b = !a;
    c = a && b || a;
    if (c)
      x = 1;
    else
      x = 0;
    System.out.println(x);
  }
}
