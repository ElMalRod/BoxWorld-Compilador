/*------------  1ra Area: Codigo de Usuario ---------*/
//------> Paquetes,importaciones
package com.elmalrod.boxworld.Analizadores;
import java_cup.runtime.*;
import java.util.LinkedList;

/*------------  2da Area: Opciones y Declaraciones ---------*/
%%
%{
    //----> Codigo de usuario en sintaxis java
    public static LinkedList<TError> TablaEL = new LinkedList<TError>();
%}

//-------> Directivas
%public
%class Lexer
%cupsym sym
%cup
%char
%column
%full
%ignorecase
%line

//------> Expresiones Regulares

numero = \d+(\.\d+)?
operacion = ([-+*/]*\d+(\.\d+)?(\s*[-+*/]\s*\d+(\.\d+)?)*)+
WHITESPACE = [\040\f\r\t\013]
//-------> keywords
UP = "up"
DOWN = "down"
LEFT = "left"
RIGHT = "right"
PUSH = "push"
FLOOR = "FLOOR"
CEIL = "CEIL"

%%
/*------------  3raa Area: Reglas Lexicas ---------*/

//------> Espacios
<YYINITIAL>{WHITESPACE} { }
//------> Comentarios
"#".* {/*Ignore*/}
//------> cosillas xD
<YYINITIAL> "+"         { System.out.println("Encontro "+yytext()+" mas" ); return new Symbol(sym.mas, yycolumn, yyline, yytext()); }
<YYINITIAL> "-"         { System.out.println("Encontro "+yytext()+" menos" ); return new Symbol(sym.menos, yycolumn, yyline, yytext()); }
<YYINITIAL> "*"         { System.out.println("Encontro "+yytext()+" por" ); return new Symbol(sym.por, yycolumn, yyline, yytext()); }
<YYINITIAL> "/"         { System.out.println("Encontro "+yytext()+" div" ); return new Symbol(sym.div, yycolumn, yyline, yytext()); }
<YYINITIAL> "("         { System.out.println("Encontro "+yytext()+" para" ); return new Symbol(sym.para, yycolumn, yyline, yytext()); }
<YYINITIAL> ")"         { System.out.println("Encontro "+yytext()+" parc" ); return new Symbol(sym.parc, yycolumn, yyline, yytext()); }
<YYINITIAL> ";"         { System.out.println("Encontro "+yytext()+" puntocoma" ); return new Symbol(sym.puntocoma, yycolumn, yyline, yytext()); }



[ \t\r\n\f]             {/* Espacios en blanco, se ignoran */}

//------> Simbolos
{UP}                   { System.out.println("Encontro "+yytext());  return new Symbol(sym.UP, yycolumn, yyline, yytext()); }
{DOWN}                 { System.out.println("Encontro "+yytext());  return new Symbol(sym.DOWN, yycolumn, yyline, yytext()); }
{LEFT}                 { System.out.println("Encontro "+yytext());  return new Symbol(sym.LEFT, yycolumn, yyline, yytext()); }
{RIGHT}                { System.out.println("Encontro "+yytext());  return new Symbol(sym.RIGHT, yycolumn, yyline, yytext()); }
{PUSH}                 { System.out.println("Encontro "+yytext());  return new Symbol(sym.PUSH, yycolumn, yyline, yytext()); }
{FLOOR}               { System.out.println("Encontro "+yytext());  return new Symbol(sym.FLOOR, yycolumn, yyline, yytext()); }
{CEIL}                { System.out.println("Encontro "+yytext());  return new Symbol(sym.CEIL, yycolumn, yyline, yytext()); }
//-------> Simbolos ER
{numero}    { System.out.println("Reconocio "+yytext()+" numero"); return new Symbol(sym.numero, yycolumn, yyline, yytext()); }
{operacion} { System.out.println("Reconocio "+yytext()+" operacion"); return new Symbol(sym.operacion, yycolumn, yyline, yytext()); }


//------> Errores Lexicos
.                       { System.out.println("Error Lexico"+yytext()+" Linea "+yyline+" Columna "+yycolumn);
                          TError datos = new TError(yytext(),yyline,yycolumn,"Error Lexico","Simbolo no existe en el lenguaje");
                          TablaEL.add(datos);}
