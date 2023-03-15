package com.elmalrod.boxworld

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.elmalrod.boxworld.Analizadores.Lexer
import com.elmalrod.boxworld.Analizadores.parser
import java.io.File
import java.io.StringReader


class GameActivity: AppCompatActivity() {

    private val READ_REQUEST_CODE: Int = 42

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gamelayout)

        val playButton = findViewById<Button>(R.id.buttoncompilegame)
        playButton.setOnClickListener {
            val mensaje = findViewById<EditText>(R.id.editTextTextMultiLinegame).text.toString()
            //System.out.println(mensaje)
                val lexico = Lexer(StringReader(mensaje))
                val sintactico = parser(lexico)
                sintactico.parse()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            resultData?.data?.also { uri ->
                val file = File(uri.path)
                // Manejar el archivo aquí
            }
        }
    }
}

