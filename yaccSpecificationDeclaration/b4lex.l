%{
	#include<stdio.h>
	#include"y.tab.h"
%}
CHARACTER [A-Za-z]
VARIABLE	{CHARACTER}[A-Za-z0-9]*
DIGIT	[0-9]
NUMBER	{DIGIT}{DIGIT}+
DECIMAL	{DIGIT}+[.]{DIGIT}*
BOOLVAL1	true|false
STRING1	["|']{VARIABLE}["|']
%%
int	{printf("%s is Int-datatype.\n",yytext);return aint;}
float	{printf("%s is Float-datatype.\n",yytext);return afloat;}
char	{printf("%s is Character-datatype.\n",yytext);return achar;}
boolean	{printf("%s is Boolean-datatype.\n",yytext);return abool;}
true|false {printf("%s is Boolean value\n",yytext);return boolval;}
String	{printf("%s is a String-datatype.\n",yytext);return aString;}
{VARIABLE}	{printf("%s is Variable-name.\n",yytext);return var;}
"="	{printf("%s is assignment operator.\n",yytext);return equ;}	
{DIGIT}	{printf("%s is a digit.\n",yytext);return digit;}
{NUMBER}	{printf("%s is a Number.\n",yytext);return number;}
{DECIMAL}	{printf("%s is a Decimal.\n",yytext);return decimal;}
"'"{CHARACTER}"'"	{printf("%s is a Character.\n",yytext);return charval;}
{STRING1}	{printf("%s is a String.\n",yytext);return Stringval;}
" "	;
","	{printf("%s is a Comma.\n",yytext);return comma;}
[\n]	{printf("New line.\n");return newl;}
";"	{printf("%s is a Semi-colon.\n",yytext);return sc;}
%%
int yywrap()
{
	return 1;
}