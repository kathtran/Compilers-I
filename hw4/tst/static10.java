// test uninitialized variables
//
class test {
  public int go(int i) {
    int j;
    System.out.println(i);
    return j;		// k uninitialized
  }
  public static void main(String[] a) {
    int i = g(1);
  }
}
