package Analizadores;

public class TError {

    String lexema, tipo, descripcion;
    int linea, columna;
    private TError siguiente;

    public TError(String le, int li, int co, String t, String de) {
        lexema = le;
        linea = li;
        columna = co;
        tipo = t;
        descripcion = de;
    }

    public TError(String le, int li, int co, String t, String de, TError siguiente) {
        lexema = le;
        linea = li;
        columna = co;
        tipo = t;
        descripcion = de;
        this.siguiente = siguiente;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public TError getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(TError siguiente) {
        this.siguiente = siguiente;
    }

}
