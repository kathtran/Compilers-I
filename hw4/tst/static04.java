// test unreachable statement
//
class test {
  public static void main(String[] a) {
    int i;
    while (true) {
      return;
      i = 5;	// unreachable
    }
    i = 3;	// OK
  }
}
