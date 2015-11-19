// test uninitialized variables
//
class test {
  public void go(int i) {
    System.out.println(i);
  }
  public static void main(String[] a) {
    int j;
    if (true)
      j = 1;
    go(j);		// j uninitialized
  }
}
