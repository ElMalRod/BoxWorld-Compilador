package com.elmalrod.boxworld

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.*
import com.elmalrod.boxworld.AnalizadoresXML.Lexer
import com.elmalrod.boxworld.AnalizadoresXML.TReporte
import com.elmalrod.boxworld.AnalizadoresXML.parser
import com.elmalrod.boxworld.AnalizadoresXML.parser.TablaRE
import com.elmalrod.boxworld.Controladores.ErroresTabla
import com.elmalrod.boxworld.Controladores.Tabla
import java.io.FileReader


class MainActivity : AppCompatActivity() {

    private val conexion = Conexion(this)
    val erroresTabla = ErroresTabla() // instanciar la clase ErroresTabla

    private lateinit var textView4: TextView

    private val READ_REQUEST_CODE: Int = 42

    @SuppressLint("WrongViewCast")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView4 = findViewById(R.id.textView4)


        val sendButton = findViewById<Button>(R.id.button)
        sendButton.setOnClickListener {
            val mensaje = findViewById<EditText>(R.id.editTextTextMultiLine).text.toString()
            conexion.enviarMensajes(mensaje)
            val stringBuilder = StringBuilder()
            erroresTabla.listaReportes(TablaRE, textView4)


        }


        val buttonLoadFile = findViewById<Button>(R.id.button_tabla)
        buttonLoadFile.setOnClickListener {
            //analizar errores

            //val listaDeReportes: List<TReporte> = obtenerListaDeReportes()
            val tabla = Tabla(this, TablaRE)

            val tableParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )

            val tablaContainer = LinearLayout(this)
            tablaContainer.orientation = LinearLayout.VERTICAL
            tablaContainer.addView(tabla)

            setContentView(tablaContainer, tableParams)
        }

        val buttonPlay = findViewById<ImageButton>(R.id.buttonPlay)
        buttonPlay.setOnClickListener {
            changeToGameLayout()
        }

        val buttonViewWorlds = findViewById<ImageButton>(R.id.buttonViewWorlds)
        buttonViewWorlds.setOnClickListener {
            changeToWorldLayout()
        }
    }

    fun changeToGameLayout() {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    fun changeToWorldLayout() {
        val intent = Intent(this, WorldActivity::class.java)
        startActivity(intent)
    }


}





