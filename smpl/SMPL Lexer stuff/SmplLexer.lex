package smpl.lang;

import smpl.sys.*;
import java_cup.runtime.*;
import java.io.*;

%%

%cup
%public

%class SmplLexer

%type java_cup.runtime.Symbol


%eofval{
    	return new Symbol(sym.EOF);
%eofval}

%char
%line

%state COMMENT STRING

%{

    int comNestLvl;

    public int getChar()
    {
	return yychar + 1;
    }

    public int getLine()
    {
	return yyline + 1;
    }

    public String getText()
    {
	return yytext();
    }
%}


nl = \n\r
cc = \b\f\n\r
ws = {cc}\t" "
wss = [{ws}]
nws = [^{ws}]
int = ([0-9]+)
hex = ({int}|[a-fA-F])+
hexnum = #x{hex}
bin = #b(0|1)+
double = {int}?\.{int}
alpha = [A-Za-z_]
num =[0-9]+
digit = -?{num}
alphanum = [{alpha}]|[{num}]
metaSym = [#!?*.]
string_text=(\\\"|[^\n\"]|\\{ws}+\\)*

chr = \'{alpha}\'
char = #\\({nws}|({hex}{hex}{hex}{hex}))
id = {alpha}{alpha}+|{metaSym}



%%

<YYINITIAL>	[{nl}]			{
					 yychar = 0;
					}
					
<YYINITIAL>	{wss}			{}

<YYINITIAL>	\//.*			{/* ignore inline comments */}
<YYINITIAL>	/\*			{
					  yybegin(COMMENT);
					  cmntNestLvl = 0;
					 }
<COMMENT>	/\*			{cmntNestLvl++;}
<COMMENT>	\*/			{
					  if (cmntNestLvl > 0)
					      cmntNestLvl--;
					  else {
					      yybegin(YYINITIAL);
					  }
					}
<COMMENT>	.|[{nl}]		{/* ignore everything else */}

<YYINITIAL>	(-)?{int}		{return new Symbol(sym.INTEGER,
						new Integer (yytext()));}
<YYINITIAL>	{hexnum}		{return new Symbol(sym.HEXINT,
						new Integer (Integer.parseInt(yytext().substring(2), 16)));}
<YYINITIAL>	{bin}			{return new Symbol(sym.BININT,
						new Integer (Integer.parseInt(yytext().substring(2), 2)));}
<YYINITIAL>	{double}		{return new Symbol(sym.DOUBLE,
						new Double (yytext()));}

<YYINITIAL>	{char}			{return new Symbol(sym.CHAR, yytext().substring(2));}

<YYINITIAL>	\"			{yybegin(STRING); return new Symbol(sym.OPENQUOTE);}

<STRING>	(\\)(\")		{return new Symbol(sym.STRING, "\"");}
<STRING>	\\			{return new Symbol(sym.STRING, "\\");}
<STRING>	(\\)n			{return new Symbol(sym.STRING, "\n");}
<STRING>	(\\)t			{return new Symbol(sym.STRING, "\t");}
<STRING>	([^{nl}\"\\])*		{return new Symbol(sym.STRING, yytext());}
<STRING>	\"			{yybegin(YYINITIAL); return new Symbol(sym.CLOSEQUOTE);}
<YYINITIAL>	#\\sp			{return new Symbol(sym.SPACE);}
<YYINITIAL>	#\\tb			{return new Symbol(sym.TAB);}
<YYINITIAL>	#\\nl			{return new Symbol(sym.NEWLINE);}
<YYINITIAL>	#\\cr			{return new Symbol(sym.CRETURN);}
<YYINITIAL>	#\\ff			{return new Symbol(sym.FORMFEED);}

<YYINITIAL>	#t			{return new Symbol(sym.TRUE);}
<YYINITIAL>	#f			{return new Symbol(sym.FALSE);}
<YYINITIAL>	#e			{return new Symbol(sym.NIL);}

<YYINITIAL>	"+"		{return new Symbol(sym.PLUS);}
<YYINITIAL>	"-"		{return new Symbol(sym.MINUS);}
<YYINITIAL>	"-"		{return new Symbol(sym.UMINUS);}
<YYINITIAL>	"*"		{return new Symbol(sym.MULTIPLY);}
<YYINITIAL>	"/"		{return new Symbol(sym.DIVIDE);}
<YYINITIAL>	"%"		{return new Symbol(sym.MODULO);}
<YYINITIAL>	"^"		{return new Symbol(sym.POWER);}

<YYINITIAL>	("&&"|and)	{return new Symbol(sym.AND);}
<YYINITIAL>	("||"|or)	{return new Symbol(sym.OR);}
<YYINITIAL>	("!"|not)	{return new Symbol(sym.NOT);}

<YYINITIAL>	"&"		{return new Symbol(sym.BAND);}
<YYINITIAL>	"|"		{return new Symbol(sym.BOR);}
<YYINITIAL>	"~"		{return new Symbol(sym.BNOT);}

<YYINITIAL>	<		{return new Symbol(sym.LT);}
<YYINITIAL>	<=		{return new Symbol(sym.LE);}
<YYINITIAL>	>		{return new Symbol(sym.GT);}
<YYINITIAL>	>=		{return new Symbol(sym.GE);}
<YYINITIAL>	=		{return new Symbol(sym.EQ);}
<YYINITIAL>	!=		{return new Symbol(sym.NE);}
<YYINITIAL>	"@"		{return new Symbol(sym.CONCAT);}

<YYINITIAL>	"[:"			{return new Symbol(sym.VLBRACKET);}
<YYINITIAL>	":]"			{return new Symbol(sym.VRBRACKET);}
<YYINITIAL>	"["			{return new Symbol(sym.LBRACKET);}
<YYINITIAL>	"]"			{return new Symbol(sym.RBRACKET);}
<YYINITIAL>	"("			{return new Symbol(sym.LPAREN);}
<YYINITIAL>	")"			{return new Symbol(sym.RPAREN);}
<YYINITIAL>	"{"			{return new Symbol(sym.LBRACE);}
<YYINITIAL>	"}"			{return new Symbol(sym.RBRACE);}
<YYINITIAL>	:=			{return new Symbol(sym.ASSIGN);}


<YYINITIAL>	pair			{return new Symbol(sym.PAIR);}
<YYINITIAL>     pr                      {return tok(sym.PAIR);}
<YYINITIAL>	car			{return new Symbol(sym.CAR);}
<YYINITIAL>	cdr			{return new Symbol(sym.CDR);}
<YYINITIAL>	"pair?"			{return new Symbol(sym.ISPAIR);}
<YYINITIAL>	list			{return new Symbol(sym.LIST);}
<YYINITIAL>	size			{return new Symbol(sym.SIZE);}
<YYINITIAL>	"eqv?"			{return new Symbol(sym.ISEQV);}
<YYINITIAL>	"equal?"		{return new Symbol(sym.ISEQ);}
<YYINITIAL>	substr			{return new Symbol(sym.SUBSTR);}
<YYINITIAL>	call			{return new Symbol(sym.CALL);}

<YYINITIAL>	proc			{return new Symbol(sym.PROC);}
<YYINITIAL>     lazy                    {return new Symbol (sym.LAZY);}
<YYINITIAL>	let			{return new Symbol(sym.LET);}
<YYINITIAL>	be			{return new Symbol(sym.BE);}
<YYINITIAL>	def			{return new Symbol(sym.DEF);}
<YYINITIAL>	"if"			{return new Symbol(sym.IF);}
<YYINITIAL>	"then"			{return new Symbol(sym.THEN);}
<YYINITIAL>	"else"			{return new Symbol(sym.ELSE);}
<YYINITIAL>	"case"			{return new Symbol(sym.CASE);}
<YYINITIAL>	print			{return new Symbol(sym.PRINT);}
<YYINITIAL>	println			{return new Symbol(sym.PRINTLN);}
<YYINITIAL>	read			{return new Symbol(sym.READ);}
<YYINITIAL>	readint			{return new Symbol(sym.READINT);}

<YYINITIAL>	for			{return new Symbol(sym.FOR);}
<YYINITIAL>	while			{return new Symbol(sym.WHILE);}

<YYINITIAL>	break			{return new Symbol(sym.BREAK);}
<YYINITIAL>	try			{return new Symbol(sym.TRY);}
<YYINITIAL>	catch			{return new Symbol(sym.CATCH);}
<YYINITIAL>	thread                  {return new Symbol(sym.THREAD);}
<YYINITIAL>	"start"			{return new Symbol(sym.START);}

<YYINITIAL>	"displayWindow"		{return new Symbol(sym.DISPLAYWINDOW);}
<YYINITIAL>	"displayPopup"		{return new Symbol(sym.DISPLAYPOPUP);}
<YYINITIAL>	"addFilebar"		{return new Symbol(sym.ADDFILEBAR);}
<YYINITIAL>	"addTextArea"		{return new Symbol(sym.ADDTEXTAREA);}
<YYINITIAL>	"addButton"		{return new Symbol(sym.ADDBUTTON);}
<YYINITIAL>	"createWindow"		{return new Symbol(sym.CREATEWINDOW);}
<YYINITIAL>	"append"		{return new Symbol(sym.APPEND);}
<YYINITIAL>	"sleep"                 {return new Symbol(sym.SLEEP);}
<YYINITIAL>	"databaseConnect"       {return new Symbol(sym.DATABASECONNECT);}
<YYINITIAL>	"databaseExec"          {return new Symbol(sym.DATABASEEXEC);}
<YYINITIAL>	"databaseResult"        {return new Symbol(sym.DATABASERESULT);}
<YYINITIAL>    "class"                  {return new Symbol(sym.CLASS);}
<YYINITIAL>    "method"                 {return new Symbol(sym.METHOD);}
<YYINITIAL>    "constr"                 {return new Symbol(sym.CONSTRUCTOR);}
<YYINITIAL>    "new"                    {return new Symbol(sym.NEW);}
<YYINITIAL>    "export"                 {return new Symbol(sym.EXPORT);}
<YYINITIAL>    "shared"                 {return new Symbol(sym.SHARED);}

<YYINITIAL>    {digit}                  {return new Symbol (sym.INTEGER, new Integer(yytext())); }
<YYINITIAL>    {id}                     {return new Symbol (sym.ID, yytext());}
<YYINITIAL>    :{id}                    {return new Symbol (sym.VAR, yytext());}
<YYINITIAL>    \"{string_text}\"        {String str = yytext().substring(1,yytext().length() - 1);
                                        return new Symbol  (sym.STRING, new String(str)); }

<YYINITIAL>	:			{return new Symbol(sym.COLON);}
<YYINITIAL>	;			{return new Symbol(sym.SEMI);}
<YYINITIAL>	,			{return new Symbol(sym.COMMA);}
<YYINITIAL>	"."                     {return new Symbol(sym.DOT);}

<YYINITIAL>	"*/"			{
					  throw new java.io.IOException(":" + yyline
					    + ": '*/' Trying to terminate block comment " +
					    "that doesn't exist. ");
					}

<YYINITIAL>	.			{
					  throw new java.io.IOException(":" + yyline
					    + ": Unrecognised input: " +
					    yytext());
					}

