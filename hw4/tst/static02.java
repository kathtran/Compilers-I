// test unreachable statement
//
class test {
  public static void main(String[] a) {
    int i;
    {
      i = 2;
      return;
    }
    System.out.println("hello");	// unreachable
  }
}
