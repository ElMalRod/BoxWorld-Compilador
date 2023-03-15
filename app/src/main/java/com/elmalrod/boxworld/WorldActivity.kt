package com.elmalrod.boxworld

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class WorldActivity : AppCompatActivity() {

    private val READ_REQUEST_CODE: Int = 42

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_worlds)
        val enviar = findViewById<Button>(R.id.button2)
        enviar.setOnClickListener {

        }
    }


}