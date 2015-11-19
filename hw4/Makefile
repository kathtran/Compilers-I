#----------------------------------------------------------------------
# CS321 Assignment 4 (Fall 2015)
#----------------------------------------------------------------------

JFLAGS = -g
JC = javac
JCC = javacc

.SUFFIXES: .jj .java .class

.PRECIOUS: %.java

.jj.java:
	$(JCC) $*.jj

.java.class:
	$(JC) $(JFLAGS) $*.java

all:	Ast.class AstParser.class StaticCheck.class 

clean:	
	rm *.class
