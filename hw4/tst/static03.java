// test unreachable statement
//
class test {
  public static void main(String[] a) {
    int i;
    if (true) { 
      i = 1;
      return;
    } else {
      i = 2;	// OK
      return;
    }
    i = 5;	// unreachable
  }
}
