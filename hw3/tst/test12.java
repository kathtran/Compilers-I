// miniJava test program (For CS321 Language and Compiler Design, PSU)
//
// Variable and method shared the same name
// 
class Test {
  int foo;
  public int foo(int i) {
    return i;
  }
  public static void main(String[] ignore) {
    Test x = new Test();
    x.foo = x.foo(1);
    System.out.println(x.foo);
  }
}
