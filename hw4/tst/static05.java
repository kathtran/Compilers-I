// test unreachable statement
//
class test {
  public int go() {
    return 1;
    return 2;	// unreachable
  }
  public static void main(String[] a) {
    int i;
    if (true)
      return;
    if (false)
      i = go();
  }
}
