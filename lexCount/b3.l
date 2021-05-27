%{
	#include<stdio.h>
	int ch=0,word=0,line=0;
%}
WORD	[a-zA-Z]+
%%
{WORD}	{word++;ch+=yyleng;}
"\n"	line++;
%%
int yywrap()
{
	return 1;
}
int main(int ac,char** av)
{
	FILE *fd;
	if(ac==2)
	{
		if(!(fd=fopen(av[1],"r")))
		{
			printf("Error in opening file");
			return -1;
		}
		yyset_in(fd);
		yylex();
		fclose(fd);
		printf("\nNumber of characters in the file are:%d\n",ch);
		printf("Number of words in the file are:%d\n",word);
		printf("Number of lines in the file are:%d\n",line+1);
	}
	else
		printf("Enter the name of the file to be read");
	return 0;
}