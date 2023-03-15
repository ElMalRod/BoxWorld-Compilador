/*-----------  1ra Area: Codigo de Usuario ---------*/
package com.elmalrod.boxworld.AnalizadoresXML;
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
%unicode
%type java_cup.runtime.Symbol
%char
%column
%ignorecase
%line
%cup

//------> Expresiones Regulares
cadena = ([a-zA-Z0-9 ]+)
numero = \d+(\.\d+)?
WHITESPACE = [\040\f\r\t\013]
//-------> keywords
ERROR_START ="<error>"
ERROR_END= "</error>"
ERRORS_START ="<errors>"
ERRORS_END = "</errors>"
LEXEMA_START = "<lexema>"
LEXEMA_END = "</lexema>"
TIPO_START = "<type>"
TIPO_END = "</type>"
LINE_START = "<line>"
LINE_END = "</line>"
COLUMN_START = "<column>"
COLUMN_END = "</column>"
DESCRIPCION_START = "<description>"
DESCRIPCION_END = "</description>"

%%
/*------------  3raa Area: Reglas Lexicas ---------*/

//-----> Simbolos
//------> Espacios
<YYINITIAL>{WHITESPACE} { }


[ \t\r\n\f]             {/* Espacios en blanco, se ignoran */}
{ERROR_START}                 {  System.out.println("Encontro "+yytext() ); return new Symbol(sym.ERROR_START, yycolumn, yyline, yytext());}
{ERROR_END}                  {  System.out.println("Encontro "+yytext() ); return new Symbol(sym.ERROR_END, yycolumn, yyline, yytext()); }
{ERRORS_START}                  {  System.out.println("Encontro "+yytext() );return new Symbol(sym.ERRORS_START, yycolumn, yyline, yytext()); }
{ERRORS_END}                 {  System.out.println("Encontro "+yytext() );return new Symbol(sym.ERRORS_END, yycolumn, yyline, yytext()); }
{LEXEMA_START}                   {  System.out.println("Encontro "+yytext() );return new Symbol(sym.LEXEMA_START, yycolumn, yyline, yytext()); }
{LEXEMA_END}                   {  System.out.println("Encontro "+yytext() );return new Symbol(sym.LEXEMA_END, yycolumn, yyline, yytext()); }
{TIPO_START}                    { System.out.println("Encontro "+yytext() ); return new Symbol(sym.TIPO_START, yycolumn, yyline, yytext()); }
{TIPO_END}                   { System.out.println("Encontro "+yytext() ); return new Symbol(sym.TIPO_END, yycolumn, yyline, yytext()); }
{LINE_START}                    { System.out.println("Encontro "+yytext() ); return new Symbol(sym.LINE_START, yycolumn, yyline, yytext()); }
{LINE_END}                   {  System.out.println("Encontro "+yytext() );return new Symbol(sym.LINE_END, yycolumn, yyline, yytext()); }
{COLUMN_START}                    { System.out.println("Encontro "+yytext() ); return new Symbol(sym.COLUMN_START, yycolumn, yyline, yytext()); }
{COLUMN_END}                  {  System.out.println("Encontro "+yytext() );return new Symbol(sym.COLUMN_END, yycolumn, yyline, yytext()); }
{DESCRIPCION_START}                {  System.out.println("Encontro "+yytext() );return new Symbol(sym.DESCRIPCION_START, yycolumn, yyline, yytext()); }
{DESCRIPCION_END}               {  System.out.println("Encontro "+yytext() );return new Symbol(sym.DESCRIPCION_END, yycolumn, yyline, yytext()); }
{numero}    { System.out.println("Encontro "+yytext() ); return new Symbol(sym.numero, yycolumn, yyline, yytext()); }
{cadena}    { System.out.println("Encontro "+yytext() ); return new Symbol(sym.TEXT, yycolumn, yyline, yytext()); }


//------> Errores Lexicos
.                       { System.out.println("Error Lexico"+yytext()+" Linea "+yyline+" Columna "+yycolumn);
                          TError datos = new TError(yytext(),yyline, yycolumn,"Lexico","Simbolo no existe en el lenguaje");
                          TablaEL.add(datos);

}