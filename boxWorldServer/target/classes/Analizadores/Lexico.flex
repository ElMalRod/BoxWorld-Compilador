/*------------  1ra Area: Codigo de Usuario ---------*/
//------> Paquetes,importaciones
package Analizadores;
import java_cup.runtime.*;
import java.util.LinkedList;
import com.malrod.boxworldserver.*;
import Controladores.*;

/*------------  2da Area: Opciones y Declaraciones ---------*/
%%
%{
    //----> Codigo de usuario en sintaxis java
    public static LinkedList<TError> TablaEL = new LinkedList<TError>(); 
    public static BoxWorldServer mandar = new BoxWorldServer();
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
comilla = "\"" 
operacion = ([-+*/]*\d+(\.\d+)?(\s*[-+*/]\s*\d+(\.\d+)?)*)+
cadena = [a-zA-Z_][a-zA-Z][a-zA-Z0-9]*
HEX_COLOR = "#"[A-Fa-f0-9]{6}|[A-Fa-f0-9]{3};
WHITESPACE = [\040\f\r\t\013]
//-------> keywords
NAME = "name"
ROWS = "rows"
COLS = "cols"
CONFIG = "config"
BOX_COLOR = "box_color"
BOX_ON_TARGET_COLOR = "box_on_target_color"
TARGET_COLOR = "target_color"
BRICK_COLOR = "brick_color"
HALL_COLOR = "hall_color"
UNDEFINED_COLOR = "undefined_color"
PLAYER_COLOR = "player_color"
BOARD = "board"
POSX = "posX"
POSY = "posY"
TYPE = "type"
BOXES = "boxes"
TARGETS = "targets"
PLAYER = "player"
BRICK = "BRICK"
HALL = "HALL"
FLOOR = "FLOOR"
CEIL = "CEIL"

%%
/*------------  3raa Area: Reglas Lexicas ---------*/

//-----> Simbolos
//------> Espacios
<YYINITIAL>{WHITESPACE} { }
<YYINITIAL> "+"         { System.out.println("Encontro "+yytext()+" mas" ); return new Symbol(sym.mas, yycolumn, yyline, yytext()); }
<YYINITIAL> "-"         { System.out.println("Encontro "+yytext()+" menos" ); return new Symbol(sym.menos, yycolumn, yyline, yytext()); }
<YYINITIAL> "*"         { System.out.println("Encontro "+yytext()+" por" ); return new Symbol(sym.por, yycolumn, yyline, yytext()); }
<YYINITIAL> "/"         { System.out.println("Encontro "+yytext()+" div" ); return new Symbol(sym.div, yycolumn, yyline, yytext()); }
<YYINITIAL> "("         { System.out.println("Encontro "+yytext()+" para" ); return new Symbol(sym.para, yycolumn, yyline, yytext()); }
<YYINITIAL> ")"         { System.out.println("Encontro "+yytext()+" parc" ); return new Symbol(sym.parc, yycolumn, yyline, yytext()); }
<YYINITIAL> ":"         { System.out.println("Encontro "+yytext()+" dospuntos" ); return new Symbol(sym.dospuntos, yycolumn, yyline, yytext()); }
<YYINITIAL> "{"         { System.out.println("Encontro "+yytext()+" llavea" ); return new Symbol(sym.llavea, yycolumn, yyline, yytext()); }
<YYINITIAL> "}"         { System.out.println("Encontro "+yytext()+" llaveb" ); return new Symbol(sym.llaveb, yycolumn, yyline, yytext()); }
<YYINITIAL> "["         { System.out.println("Encontro "+yytext()+" cora" ); return new Symbol(sym.cora, yycolumn, yyline, yytext()); }
<YYINITIAL> "]"         { System.out.println("Encontro "+yytext()+" corb" ); return new Symbol(sym.corb, yycolumn, yyline, yytext()); }
<YYINITIAL> ","         { System.out.println("Encontro "+yytext()+" coma" ); return new Symbol(sym.coma, yycolumn, yyline, yytext()); }


[ \t\r\n\f]             {/* Espacios en blanco, se ignoran */}
{NAME}                { System.out.println("Encontro "+yytext()); return new Symbol(sym.NAME, yycolumn, yyline, yytext()); }
{ROWS}                { System.out.println("Encontro "+yytext());  return new Symbol(sym.ROWS,yycolumn, yyline, yytext()); }
{COLS}                { System.out.println("Encontro "+yytext());  return new Symbol(sym.COLS,yycolumn, yyline, yytext()); }
{CONFIG}              { System.out.println("Encontro "+yytext());  return new Symbol(sym.CONFIG, yycolumn, yyline, yytext()); }
{HEX_COLOR}           {System.out.println("Encontro "+yytext());  return new Symbol(sym.HEX_COLOR,yycolumn, yyline, yytext()); }
{BOX_COLOR}           { System.out.println("Encontro "+yytext());  return new Symbol(sym.BOX_COLOR, yycolumn, yyline, yytext()); }
{BOX_ON_TARGET_COLOR} { System.out.println("Encontro "+yytext());  return new Symbol(sym.BOX_ON_TARGET_COLOR,yycolumn, yyline, yytext()); }
{TARGET_COLOR}        { System.out.println("Encontro "+yytext());  return new Symbol(sym.TARGET_COLOR, yycolumn, yyline, yytext()); }
{BRICK_COLOR}         { System.out.println("Encontro "+yytext());  return new Symbol(sym.BRICK_COLOR, yycolumn, yyline, yytext()); }
{HALL_COLOR}          { System.out.println("Encontro "+yytext());  return new Symbol(sym.HALL_COLOR, yycolumn, yyline, yytext()); }
{UNDEFINED_COLOR}     { System.out.println("Encontro "+yytext());  return new Symbol(sym.UNDEFINED_COLOR, yycolumn, yyline, yytext()); }
{PLAYER_COLOR}        { System.out.println("Encontro "+yytext());  return new Symbol(sym.PLAYER_COLOR, yycolumn, yyline, yytext()); }
{BOARD}               { System.out.println("Encontro "+yytext());  return new Symbol(sym.BOARD, yycolumn, yyline, yytext()); }
{POSX}                { System.out.println("Encontro "+yytext());  return new Symbol(sym.POSX,yycolumn, yyline, yytext()); }
{POSY}                { System.out.println("Encontro "+yytext());  return new Symbol(sym.POSY, yycolumn, yyline, yytext()); }
{TYPE}                { System.out.println("Encontro "+yytext());  return new Symbol(sym.TYPE,yycolumn, yyline, yytext()); }
{BOXES}               { System.out.println("Encontro "+yytext());  return new Symbol(sym.BOXES, yycolumn, yyline, yytext()); }
{TARGETS}             { System.out.println("Encontro "+yytext());  return new Symbol(sym.TARGETS, yycolumn, yyline, yytext()); }
{PLAYER}              { System.out.println("Encontro "+yytext());  return new Symbol(sym.PLAYER, yycolumn, yyline, yytext()); }
{BRICK}               { System.out.println("Encontro "+yytext());  return new Symbol(sym.BRICK, yycolumn, yyline, yytext()); }
{HALL}                { System.out.println("Encontro "+yytext());  return new Symbol(sym.HALL, yycolumn, yyline, yytext()); }
{FLOOR}               { System.out.println("Encontro "+yytext());  return new Symbol(sym.FLOOR, yycolumn, yyline, yytext()); }
{CEIL}                { System.out.println("Encontro "+yytext());  return new Symbol(sym.CEIL, yycolumn, yyline, yytext()); }
//-------> Simbolos ER
{cadena}    { System.out.println("Reconocio "+yytext()+" cadena"); return new Symbol(sym.cadena, yycolumn, yyline, yytext()); }
{comilla}   {System.out.println("Encontro "+yytext()); return new Symbol(sym.comilla,yycolumn, yyline, yytext()); }
{numero}    { System.out.println("Reconocio "+yytext()+" numero"); return new Symbol(sym.numero, yycolumn, yyline, yytext()); }
{operacion} { System.out.println("Reconocio "+yytext()+" operacion"); return new Symbol(sym.operacion, yycolumn, yyline, yytext()); }


//------> Errores Lexicos
.                       { System.out.println("Error Lexico"+yytext()+" Linea "+yyline+" Columna "+yycolumn);
                          TError datos = new TError(yytext(),yyline, yycolumn,"lexico","Simbolo no existe en el lenguaje");
                          TablaEL.add(datos);
                          mandar.tablaErrores(yytext(),yyline,yycolumn,"lexico","Simbolo no existe");}


