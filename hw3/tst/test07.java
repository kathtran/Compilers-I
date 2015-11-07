// miniJava test program (For CS321 Language and Compiler Design, PSU)
//
// Operator precedence
// 
class Test {
  public static void main(String[] ignore) {
    boolean b;
    int i = 0;
    int[] a = new int[4];
    b = 1 < 2 || 3 > 4 && 5 == 6 + 7 * 8 || ! true;
    i =  - - 3 + 5 * 4 / 2 * a[1] + i * 2;
    System.out.println(b);
    System.out.println(i);
  }
}
