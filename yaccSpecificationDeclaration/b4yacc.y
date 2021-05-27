%{
	#include<stdio.h>
	void yyerror(char*);
	int yylex();
	FILE *yyin;
%}
%token aint afloat achar abool boolval aString var equ digit number decimal charval Stringval comma newl sc
%%
s:	I	{printf("Valid Integer Declaration\n");return 0;}
|F	{printf("Valid Float Declaration\n");return 0;}
|C	{printf("Valid Character Declaration\n");return 0;}
|B	{printf("Valid Boolean Declaration\n");return 0;}
|S	{printf("Valid String Declaration\n");return 0;}
;
I:	aint I1 sc
|	aint I1 sc newl
F:	afloat F1 sc
|	afloat F1 sc newl
C:	achar C1 sc
|	achar C1 sc newl
B:	abool B1 sc
|	abool B1 sc newl
S:	aString S1 sc
|	aString S1 sc newl
;
I1:	var|var comma I1|var equ digit|var equ digit comma I1|var equ number|var equ number comma I1
;
F1:	var|var comma F1|var equ digit|var equ digit comma F1|var equ number|var equ number comma F1|var equ decimal|var equ decimal comma F1
;
C1:	var|var comma C1|var equ charval|var equ charval comma C1
;
B1:	var|var comma B1|var equ boolval|var equ boolval comma B1
;
S1:	var|var comma S1|var equ Stringval|var equ Stringval comma S1
;
%%
int main()
{
   	yyin=fopen("test.txt","r");
   	yyparse();
   	return 0;
}
void yyerror(char *s){
    fprintf(stderr, "Error!!: %s\n",s);
}
