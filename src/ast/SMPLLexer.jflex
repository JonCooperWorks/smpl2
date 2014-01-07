package ast;

//User Customisations
import java_cup.runtime.*;
import java.io.IOException;

//JFlex Directives
%%

%cup
%public

%class SMPLLexer

%type java_cup.runtime.Symbol

%eofval{
	return new Symbol(sym.EOF);
%eofval}

%eofclose false

%char
%column
%line

%{
   public int getChar(){
	return yychar +1;
   }

   public int getColumn(){
	return yycolumn +1;
   }

   public int getLine(){
	return yyline + 1;
   }

   public String getText(){
	return yytext();
   }
%}

validComment = [^\r\n] 

nl = [\n\r]|\r\n

cc = ([\b\f]|{nl}) 

ws = {cc}|[\t ]
 
hxdigit = [0-9a-fA-F]

alpha = [a-zA-Z]

num = [0-9]

alphanum = {alpha}|{num}

specialchars = ["#""+""-""*"".""!"]

allchars={alphanum}|{specialchars}

identifier = {alphanum}+{allchars}*

 
%%

// Symbol and Operators
<YYINITIAL>	{ws}	{/* ignore whitespace */}
<YYINITIAL>     "//"    {validComment}* {nl} { /* ignore comments */ }
<YYINITIAL>     \/\*([^*]|\*[^/])*\*+\/ { /* comments */ }
<YYINITIAL>	"+"	{return new Symbol(sym.PLUS);}
<YYINITIAL>	"-"	{return new Symbol(sym.MINUS);}
<YYINITIAL>	"*"	{return new Symbol(sym.MUL);}
<YYINITIAL>	"/"	{return new Symbol(sym.DIV);}
<YYINITIAL>	"("	{return new Symbol(sym.LPAREN);}
<YYINITIAL>	")"	{return new Symbol(sym.RPAREN);}
<YYINITIAL>     "["     {return new Symbol(sym.LBRACE); }
<YYINITIAL>     "]"     {return new Symbol(sym.RBRACE); }
<YYINITIAL>	","	{return new Symbol(sym.COMMA);}
<YYINITIAL>     "%"     {return new Symbol(sym.MOD);}
<YYINITIAL>	"="	{return new Symbol(sym.EQUAL);}
<YYINITIAL>     "<"     {return new Symbol(sym.LTHAN);}
<YYINITIAL>     ">"     {return new Symbol(sym.GTHAN);}
<YYINITIAL>     "not"   {return new Symbol(sym.LNOT);}
<YYINITIAL>     "and"   {return new Symbol(sym.LAND);}
<YYINITIAL>     "or"    {return new Symbol(sym.LOR);}

<YYINITIAL>     ":="    {return new Symbol(sym.ASSIGN);}
<YYINITIAL>     "<="    {return new Symbol(sym.LEQUAL);}
<YYINITIAL>     ">="    {return new Symbol(sym.GEQUAL);}
<YYINITIAL>     "!="    {return new Symbol(sym.NEQUAL);}
<YYINITIAL>     "~"    {return new Symbol(sym.BNOT);}
<YYINITIAL>     "|"    {return new Symbol(sym.BOR);}
<YYINITIAL>     "&"    {return new Symbol(sym.BAND);}
<YYINITIAL>     "@"    {return new Symbol(sym.CONCAT);}
<YYINITIAL>     ";"    {return new Symbol(sym.SEMI);}
<YYINITIAL>     "[:"    {return new Symbol(sym.VECLEFT);}
<YYINITIAL>     ":]"    {return new Symbol(sym.VECRIGHT);}
<YYINITIAL>     ":"    {return new Symbol(sym.COLON);}
<YYINITIAL>     "{"    {return new Symbol(sym.LBRACKET);}
<YYINITIAL>     "}"    {return new Symbol(sym.RBRACKET);}


//Keywords
<YYINITIAL>	"proc"                    {return new Symbol(sym.PROC);}
<YYINITIAL>	"call"                    {return new Symbol(sym.CALL);}
<YYINITIAL>	"let"                     {return new Symbol(sym.LET);}
<YYINITIAL>     def                     {return new Symbol(sym.DEF);}
<YYINITIAL>	"if"                      {return new Symbol(sym.IF);}
<YYINITIAL>	"then"                    {return new Symbol(sym.THEN);}
<YYINITIAL>	"else"                    {return new Symbol(sym.ELSE);}
<YYINITIAL>	"case"                    {return new Symbol(sym.CASE);}
<YYINITIAL>	"print"                   {return new Symbol(sym.PRINT);}
<YYINITIAL>	"println"                 {return new Symbol(sym.PRINTLN);}
<YYINITIAL>	"read"                    {return new Symbol(sym.READ);}
<YYINITIAL>	"readint"                 {return new Symbol(sym.READINT);}
<YYINITIAL>	"lazy"                    {return new Symbol(sym.LAZY);}
<YYINITIAL>	"pair"                    {return new Symbol(sym.PAIR);}
<YYINITIAL>	"pair?"                    {return new Symbol(sym.PAIRQ);}
<YYINITIAL>	"car"                     {return new Symbol(sym.CAR);}
<YYINITIAL>	"cdr"                     {return new Symbol(sym.CDR);}
<YYINITIAL>	"list"                    {return new Symbol(sym.LIST);}
<YYINITIAL>	"size"                    {return new Symbol(sym.SIZE);}
<YYINITIAL>	"eqv?"                    {return new Symbol(sym.EQVQ);}
<YYINITIAL>	"equal?"                  {return new Symbol(sym.EQUALQ);}
<YYINITIAL>	"substr"                  {return new Symbol(sym.SUBSTR);}
<YYINITIAL>	"be"                      {return new Symbol(sym.BE);}

<YYINITIAL>	"#t"                      { return new Symbol(sym.TRUE); }
<YYINITIAL>	"#f"                      { return new Symbol(sym.FALSE); }


<YYINITIAL> \"(.+|"\t"|"\n"|"\f"|\\\\)\" {
                    return new Symbol(sym.STRING, yytext()
                        .substring(1, yylength() - 1));
                }


<YYINITIAL>        \'(.|"\t"|"\n"|\\\\|"\f"|"\'")\'        {
                    return new Symbol(sym.CHAR, yytext()
                        .substring(1, yylength() - 1));
                 }

<YYINITIAL>    {num}+ {
	       // INTEGER
	       return new Symbol(sym.INT, 
                           new Integer(yytext()));
	       }

<YYINITIAL>        #b(0|1)+              { return new Symbol( sym.BIN, Integer.parseInt( yytext().substring(2),2)); }


<YYINITIAL>        #x{hxdigit}+            { return new Symbol( sym.HEX, Integer.parseInt(yytext().substring(2), 16)); }

<YYINITIAL>    {identifier} {
	       // VARIABLE
	       return new Symbol(sym.VARIABLE, yytext());
	       }

<YYINITIAL>    {num}*\.{num}+ {
         // REAL
         return new Symbol(sym.DOUBLE,
                           new Double(yytext()));
         }
