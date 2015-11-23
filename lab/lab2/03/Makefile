#----------------------------------------------------------------------
# CS321 Assignment 1 (Winter 2015)
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

all:	lexer1 lexer2

lexer1: Lexer1.class RunLexer1.class

lexer2: Lexer2.class RunLexer2.class

clean:	
	rm *.class ./Lexer2*.java ./Parse*.java ./Simple*.java ./Token*.java

