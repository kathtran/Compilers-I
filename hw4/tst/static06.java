// test uninitialized variables
//
class test {
  int i;
  public static void main(String[] a) {
    int j;
    System.out.println(i);	// OK
    System.out.println(j);	// j uninitialized
  }
}
