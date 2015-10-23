// miniJava test program (For CS321 Language and Compiler Design, PSU)
//
// class fields
// 
class Test {
  int i;
  int j;
  public static void main(String[] ignore) {
    Test x = new Test();
    x.i = 4;
    x.j = x.i + 2;
    System.out.println(x.j);
  }
}
