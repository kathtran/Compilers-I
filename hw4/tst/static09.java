// test uninitialized variables
//
class test {
  public void go(int i) {
    System.out.println(i);
  }
  public static void main(String[] a) {
    int i;
    int j;
    int[] x = new int[2];
    if (true)
      i = 1;
    else
      i = 2;
    go(i);		// OK
    x[j] = i;		// j uninitialized
  }
}
