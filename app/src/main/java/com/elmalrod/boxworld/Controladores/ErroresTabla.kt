package com.elmalrod.boxworld.Controladores

import com.elmalrod.boxworld.AnalizadoresXML.TReporte

class ErroresTabla {

    fun listaReportes(lista: List<TReporte>) {
        for (elemento in lista) {
            println("lexema: ${elemento.lexema}")
            println("linea: ${elemento.linea}")
            println("columna: ${elemento.columna}")
            println("type: ${elemento.tipo}")
            println("descripcion: ${elemento.descripcion}")
        }
    }
}