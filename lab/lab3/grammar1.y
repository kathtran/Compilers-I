// This is supporting software for CS321/CS322 Compilers and Language Design.
// Copyright (c) Portland State University.
//---------------------------------------------------------------------------
// For CS321 F'15 (J. Li).
//

// Grammar 1:
//   A -> - A 
//   A -> A - id
//   A -> id
//

// Declarations
%{
#include <stdio.h>
#include <ctype.h>
%}
%start A
%token ID

%% // Grammar Rules

A : '-' A
  | A '-' ID
  | ID
  ; 

%% // Program Code

main() {
  yyparse();
}

yyerror(char *msg) {
  printf("%s\n", msg);
}

yylex() {
  int c;
  while ((c = getchar ()) == ' ' || c == '\n')
    ;
  if (c == EOF)
    return 0;
  if (isalpha(c))
    return ID;
  return c;
}
