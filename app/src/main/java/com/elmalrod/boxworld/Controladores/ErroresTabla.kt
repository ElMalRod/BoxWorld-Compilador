package com.elmalrod.boxworld.Controladores

import android.widget.TextView
import com.elmalrod.boxworld.AnalizadoresXML.TReporte
import com.elmalrod.boxworld.AnalizadoresXML.parser.TablaRE

class ErroresTabla {

    fun listaReportes(lista: List<TReporte>, textView4: TextView) {
        val stringBuilder = StringBuilder()

        for (elemento in TablaRE) {
            stringBuilder.append("lexema: ${elemento.lexema} linea: ${elemento.linea} type: ${elemento.tipo}")

            println("lexema: ${elemento.lexema} linea: ${elemento.linea} type: ${elemento.tipo}")
        }

        val mensajeError = stringBuilder.toString()
        textView4.text = "Error encontrado: $mensajeError"
    }

}