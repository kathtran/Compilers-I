// test unreachable statement
//
class test {
  public static void main(String[] a) {
    System.out.println("123");
    return;
    System.out.println("hello");	// unreachable
  }
}
