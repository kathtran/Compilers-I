// This is supporting software for CS321/CS322 Compilers and Language Design.
// Copyright (c) Portland State University.
//---------------------------------------------------------------------------
// For CS321 F'15 (J. Li).
//

// An AST representation for the RE language (BASE VERSION).
// - Node definitions
// - Dumping routines for two external forms (text and dot)
//
// AST Nodes:
//   Alter, Concat, Repeat, Char
//

public class Ast {
  public static abstract class Expr {

      // Generate a plain string dumping form
      abstract public String toString();

      // Generate a '.dot' format dumping form
      abstract public String toDot();

      public String dotForm() {
	return "digraph Ast {\n" 
	  + "node [shape=box,style=filled,height=.2,fontname=Courier];\n"
          + "edge [arrowhead=none];\n"
          + "0 [color=white,label=\"" + toString() + "\"];\n"
	  + toDot() + "}\n";
      }
  }      

  public static class Alter extends Expr {
    private final Expr rand1, rand2;

    public Alter(Expr rand1, Expr rand2) { 
      this.rand1 = rand1;
      this.rand2 = rand2;
    }

    public String toDot() { 
      return hashCode() + " [fillcolor=lightcyan,label=\"|\"];\n" 
	+ hashCode() + " -> " + rand1.hashCode() + ";\n"
	+ hashCode() + " -> " + rand2.hashCode() + ";\n"
	+ rand1.toDot() + rand2.toDot();
    }

    public String toString() { 
      return "(" + rand1 + "|" + rand2 + ")";
    }
  }

  public static class Concat extends Expr {
    private final Expr rand1, rand2;

    public Concat(Expr rand1, Expr rand2) { 
      this.rand1 = rand1;
      this.rand2 = rand2;
    }

    public String toDot() { 
      return hashCode() + " [fillcolor=beige,label=\"&\"];\n" 
	+ hashCode() + " -> " + rand1.hashCode() + ";\n"
	+ hashCode() + " -> " + rand2.hashCode() + ";\n"
	+ rand1.toDot() + rand2.toDot();
    }

    public String toString() { 
      return "(" + rand1 + rand2 + ")";
    }
  }

  public static class Repeat extends Expr {
    private final Expr rand;

    public Repeat(Expr rand) { 
      this.rand = rand;
    }

    public String toDot() { 
      return hashCode() + " [label=\"*\"];\n" 
	+ hashCode() + " -> " + rand.hashCode() + ";\n"
	+ rand.toDot();
    }

    public String toString() { 
      return rand + "*";
    }
  }

  private static boolean isVowel(char c) {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' 
      || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
  }

  public static class Letter extends Expr {
    private final char c;

    public Letter(char c) { 
      this.c = c;
    }

    public String toDot() { 
      return hashCode() + " [shape=ellipse,style=solid,label=\"" + c + "\"];\n";
    }

    public String toString() { 
      return "" + c;
    }
  }

}
