// This is supporting software for CS321/CS322 Compilers and Language Design.
// Copyright (c) Portland State University.
//---------------------------------------------------------------------------
// For CS321 F'15 (J. Li).
//

// A simple echo program, with file input.
//
import java.io.*;

public class Echo {
  public static void main(String [] args) throws Exception {
    if (args.length == 1) {
      FileReader input = new FileReader(args[0]);
      int c, charCnt = 0;
      while ((c = input.read()) != -1) {      // read() returns -1 on EOF
        System.out.print((char)c);
        charCnt++;
      }
      input.close();
      System.out.println("Total: " + charCnt + " characters");
    } else {
      System.err.println("Need a file name as command-line argument.");
    }
  }
}
