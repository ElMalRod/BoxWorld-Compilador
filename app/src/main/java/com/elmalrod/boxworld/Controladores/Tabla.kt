package com.elmalrod.boxworld.Controladores

import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.elmalrod.boxworld.AnalizadoresXML.TReporte
import com.elmalrod.boxworld.MainActivity

class Tabla(context: Context?, lista: List<TReporte>) : TableLayout(context) {

    init {
        createTable(lista)
    }

    private fun createTable(lista: List<TReporte>) {
        // Crear la fila de encabezado
        val headerRow = TableRow(context)
        headerRow.addView(createTextView("lexema"))
        headerRow.addView(createTextView("linea"))
        headerRow.addView(createTextView("columna"))
        headerRow.addView(createTextView("tipo"))
        headerRow.addView(createTextView("descripcion"))
        addView(headerRow)

        // Crear las filas de datos
        for (elemento in lista) {
            val dataRow = TableRow(context)
            dataRow.addView(createTextView(elemento.lexema))
            dataRow.addView(createTextView(elemento.linea))
            dataRow.addView(createTextView(elemento.columna))
            dataRow.addView(createTextView(elemento.tipo))
            dataRow.addView(createTextView(elemento.descripcion))
            addView(dataRow)
        }

        // Agregar el botón de volver
        val backButton = Button(context)
        backButton.text = "Volver"
        backButton.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
        val backRow = TableRow(context)
        backRow.addView(backButton)
        addView(backRow)
    }

    private fun createTextView(text: String): TextView {
        val textView = TextView(context)
        textView.text = text
        textView.gravity = Gravity.CENTER
        textView.setPadding(16, 16, 16, 16)
        return textView
    }
}

