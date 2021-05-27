%{
#include<stdio.h>
int counter=0;
%}
DATATYPE	void|int|float|double|long|String|Boolean
SPECIFIERS	public|protectd|public
KEYWORD	class|static|main|return|if|else|for|while|System|out|println|switch
PARA	"{"|"}"|"["|"]"|"("|")"
VARIABLE	[A-Za-z][A-Za-z0-9]*
OPERATOR	"+"|"-"|"*"|"/"|"="|"=="|"!="|">"|">="|"<="|"<"
DIGIT	[0-9]
NUMBER	{DIGIT}[0-9]+
CLASS	{SPECIFIERS}" class "{VARIABLE}
FUNCTION	{SPECIFIERS}" static "{DATATYPE}" "{VARIABLE}"()"
MFUNCTION	{VARIABLE}+" "{VARIABLE}+" "{VARIABLE}+" "{VARIABLE}+"("{VARIABLE}+"[""]"" "{VARIABLE}+")"
%%
{DATATYPE}	printf("%s\tis a Datatype.\n",yytext);
{CLASS}	printf("%s\tis a class.\n",yytext);
{MFUNCTION}	printf("%s\tis a main function.\n",yytext);
{FUNCTION}	printf("%s\tis another function.\n",yytext);
{SPECIFIERS}	printf("%s\tis Access Specifier.\n",yytext);
{KEYWORD}	printf("%s\tis a Keyword.\n",yytext);
{PARA}	printf("%s\tis a Bracket.\n",yytext);
{VARIABLE}	printf("%s\tis a Variable.\n",yytext);
{OPERATOR}	printf("%s\tis an Opeartor.\n",yytext);
{DIGIT}	printf("%s\tis a Digit.\n",yytext);
{NUMBER}	printf("%s\tis Number.\n",yytext);
" "	;
[\n\t]+	;
";"	;
","	printf("%s\tis a Comma.\n",yytext);
"."	;
.	printf("%s\tis Lexical Error.\n",yytext);
%%
int yywrap()
{
	return 1;
}
int main(int ac,char** av)
{
	
    FILE    *fd;

    if (ac == 2)
    {
        if (!(fd = fopen(av[1], "r")))
        {
            perror("Error: ");
            return (-1);
        }
        yyset_in(fd);
        yylex();
        fclose(fd);
    }
    else
        printf("Usage: a.out filename\n");
    return 0;	
}
