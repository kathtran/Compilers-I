# Makefile for CS321 Compilers and Language Design I Lab 6
# Jingke Li, Portland State University
#
JFLAGS = -g
JC = javac
JCC = javacc

.SUFFIXES: .jj .java .class

.PRECIOUS: %.java

.jj.java:
	$(JCC) $*.jj

.java.class:
	$(JC) $(JFLAGS) $*.java

all:	ast hello stmt1 stmt2 stmt3 expr1 expr2

ast:	ast/Ast.class
hello:	HelloAst.class
sum:	SumAst.class
stmt1:	Stmt1.class
stmt2:	Stmt2.class
stmt3:	Stmt3.class
expr1:	Expr1.class
expr2:	Expr2.class

clean:
	rm ast/*.class *.class Stmt*.java Expr*.java Parse*.java Token*.java Simple*.java
