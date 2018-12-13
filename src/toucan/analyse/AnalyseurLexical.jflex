package toucan.analyse;

import java_cup.runtime.*;
import toucan.arbre.*;

%%

%public
%class AnalyseurLexical

%type Symbol

%eofval{
        return symbol(CodesLexicaux.EOF) ;
%eofval}

%cup

%{
  private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn) ;
  }

  private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value) ;
  }
%}

commentSlAs = "/*" [^*] ~"*/" | "/*" "*"+ "/"
commentDoubleSl = \/\/.*\n?
type = char|int|long|float|double|boolean|byte
constanteEntiere = [0-9]+
comparateur = <=?|>=?|==|\!=
variable = [a-zA-Z]+
tableauOuvrir = {variable}\[
operation = \+|-|\*|\/
opeRapide = \+{2}

%%

{commentSlAs} {}
{commentDoubleSl} {}
{operation} { return symbol(CodesLexicaux.OPERATION, yytext()); }
{tableauOuvrir} { return symbol(CodesLexicaux.TABOUVRIR, yytext()); }
"]" { return symbol(CodesLexicaux.TABFERMER); }
{comparateur} { return symbol(CodesLexicaux.COMPARATEUR, yytext()); }
{type} { return symbol(CodesLexicaux.TYPEVAR, yytext()); }
";" { return symbol(CodesLexicaux.POINTVIRGULE); }
"=" { return symbol(CodesLexicaux.EGALE); }
"for" { return symbol(CodesLexicaux.BOUCLEFOR); }
"while" { return symbol(CodesLexicaux.BOUCLEWHILE); }
"(" { return symbol(CodesLexicaux.PARENTHESEOUVR); }
")" { return symbol(CodesLexicaux.PARENTHESEFERM); }
"{" { return symbol(CodesLexicaux.BRACKETOUVR); }
"}" { return symbol(CodesLexicaux.BRACKETFERM); }
"if" { return symbol(CodesLexicaux.CONDITIONNELLEIF); }
"else" { return symbol(CodesLexicaux.CONDITIONNELLEELSE); }
{variable} { return symbol(CodesLexicaux.VARIABLE, yytext()); }
{opeRapide} { return symbol(CodesLexicaux.OPERAPIDE, yytext()); }
{constanteEntiere} { return symbol(CodesLexicaux.CONSTANTE, yytext()); }
\n {}
\t {}
. {}