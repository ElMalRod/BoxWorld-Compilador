package com.elmalrod.boxworld.AnalizadoresXML;

public class TError {
    String lexema, tipo, descripcion;
    int linea, columna;

    public TError(String le, int li, int co, String t, String de) {
        lexema = le;
        linea = li;
        columna = co;
        tipo = t;
        descripcion = de;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getColumna() {
        return columna;
    }
}
