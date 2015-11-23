// This is supporting software for CS321/CS322 Compilers and Language Design.
// Copyright (c) Portland State University.
//---------------------------------------------------------------------------
// For CS321 F'15 (J. Li).
//

// A simple echo program, with file input.
//
import java.io.*;

public class Copy {
  public static void main(String [] args) throws Exception {
    if (args.length == 2) {

      //*** MY CODE ***//
      File file = new File(args[1]);
      FileWriter output = new FileWriter(file);
      //*** MY CODE ***//

      FileReader input = new FileReader(args[0]);
      int c, charCnt = 0;
      while ((c = input.read()) != -1) {      // read() returns -1 on EOF
//        System.out.print((char)c);
	output.write((char)c);
        charCnt++;
      }
      input.close();
      output.close();
      System.out.println("Total: " + charCnt + " characters");	
    } else {
      System.err.println("Need two files as command-line arguments.");
    }
  }
}
