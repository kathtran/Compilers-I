// miniJava test program (For CS321 Language and Compiler Design, PSU)
//
// Method call and return value
// 
class Test {
  int i;
  public int go() {
    int j;
    i = 4;
    j = i + 2;
    return j;
  }
  public static void main(String[] ignore) {
    Test x = new Test();
    int r = x.go();
    System.out.println(r);
  }
}
