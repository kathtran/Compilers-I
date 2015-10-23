// miniJava test program (For CS321 Language and Compiler Design, PSU)
//
// Method declarations
// 
class Test {
  public int foo(int i) {
    int x;
    return i;
  }
  public int bar(int i) {
    int x=2;
    return x;
  }
  public static void main(String[] ignore) {
    Test x = new Test();
    int i;
    int j;
    i = x.foo(1);
    j = x.bar(1);
    System.out.println(i);
    System.out.println(j);
  }
}
