package com.elmalrod.boxworld

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import okhttp3.Response
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

class WorldActivity : AppCompatActivity() {

    private val READ_REQUEST_CODE: Int = 42
    private val conexion = Conexion(this)
    private lateinit var textViewWorld: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_worlds)

        val enviar = findViewById<Button>(R.id.button2)
        enviar.setOnClickListener {
            val mensaje = findViewById<EditText>(R.id.editTextTextMultiLine2).text.toString()
            conexion.enviarMensajes(mensaje)
            textViewWorld = findViewById(R.id.textViewWorld)
            conexion.addMensajeListener {
                runOnUiThread {
                    val response = conexion.getResponse()
                    textViewWorld.text = response
                }
            }
        }


    }


}