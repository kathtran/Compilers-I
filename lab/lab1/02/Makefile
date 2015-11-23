# Makefile for CS321 Lab 1. (J. Li)
#
JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.PRECIOUS: %.java

.java.class:
	$(JC) $(JFLAGS) $*.java

all: Compiler.class Lexer.class Parser.class

clean:
	rm *.class
