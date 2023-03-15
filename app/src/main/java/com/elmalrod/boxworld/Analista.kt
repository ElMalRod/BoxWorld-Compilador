package com.elmalrod.boxworld

import com.elmalrod.boxworld.AnalizadoresXML.LexerXML
import com.elmalrod.boxworld.AnalizadoresXML.parser
import java.io.FileReader
import android.app.AlertDialog
import android.content.Context


class Analista() {

    fun leerArchivo(){
        // Aquí se crea el lexer para procesar el archivo
        val lexer = LexerXML(FileReader("/data/user/0/com.elmalrod.boxworld/files/errores.xml"))
        val sintactico = parser(lexer)
        sintactico.parse()

    }


}

