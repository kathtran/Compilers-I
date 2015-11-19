// test uninitialized variables
//
class test {
  public void go(int i) {
    int j;
    j = i + j; 		// j untinitialized
  }
  public static void main(String[] a) {
    go();
  }
}
