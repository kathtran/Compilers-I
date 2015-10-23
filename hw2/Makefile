#----------------------------------------------------------------------
# CS321 Assignment 2 (Fall 2014)
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

LLGrammar: LLGrammar.class

clean:	
	rm *.class *.java
