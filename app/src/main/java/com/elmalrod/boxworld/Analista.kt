package com.elmalrod.boxworld

import com.elmalrod.boxworld.AnalizadoresXML.Lexer
import com.elmalrod.boxworld.AnalizadoresXML.parser
import java.io.FileReader


class Analista() {


    fun leerArchivo(){
        // Aquí se crea el lexer para procesar el archivo
        val lexer1 = Lexer(FileReader("/data/user/0/com.elmalrod.boxworld/files/errores.xml"))
        val sintactico2 = parser(lexer1)
        sintactico2.parse()

    }






}

