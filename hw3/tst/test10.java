// miniJava test program (For CS321 Language and Compiler Design, PSU)
//
// Class field initialization.
//
class Test {
  int i = 2;
  int j = i;
  int k = 3;
  public static void main(String[] ignore) {
    Test x = new Test();
    System.out.println(x.i);
    System.out.println(x.j);
    System.out.println(x.k);
  }
}
