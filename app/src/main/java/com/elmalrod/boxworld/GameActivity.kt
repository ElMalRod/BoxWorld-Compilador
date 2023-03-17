package com.elmalrod.boxworld

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.elmalrod.boxworld.Analizadores.Lexer
import com.elmalrod.boxworld.Analizadores.parser
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.io.StringReader
import java.lang.Integer.min


class GameActivity: AppCompatActivity() {

    private val READ_REQUEST_CODE: Int = 42

   // private lateinit var surfaceView: SurfaceView
    //private lateinit var surfaceHolder: SurfaceHolder
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gamelayout)

        //surfaceView = findViewById(R.id.surfaceView)
      //  surfaceHolder = surfaceView.holder

        val playButton = findViewById<Button>(R.id.buttoncompilegame)
        playButton.setOnClickListener {
            val mensaje = findViewById<EditText>(R.id.editTextTextMultiLinegame).text.toString()
            //System.out.println(mensaje)
            val lexico = Lexer(StringReader(mensaje))
            val sintactico = parser(lexico)
            sintactico.parse()

        }
    }
/*
    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            resultData?.data?.also { uri -> "/home/elmalrod/BoxWorld/app/src/main/res/xml/boxworldd.xml"
                val inputStream = contentResolver.openInputStream(uri)
                val reader = BufferedReader(InputStreamReader(inputStream))
                val worldData = reader.readText()
                surfaceHolder.lockCanvas()?.let { canvas ->
                    drawBoxWorld(canvas, worldData)
                    surfaceHolder.unlockCanvasAndPost(canvas)
                }
            }
        }
    }
}


fun drawBoxWorld(canvas: Canvas, worldData: String) {
    // Analizar el archivo XML para obtener los datos del mundo
    val xmlParser = XmlPullParserFactory.newInstance().newPullParser()
    xmlParser.setInput(StringReader(worldData))
    var eventType = xmlParser.eventType
    var worldName = ""
    var rows = 0
    var cols = 0
    var boxColor = Color.YELLOW
    var boxOnTargetColor = Color.RED
    var targetColor = Color.GREEN
    var brickColor = Color.GRAY
    var hallColor = Color.BLUE
    var undefinedColor = Color.DKGRAY
    var playerColor = Color.MAGENTA
    val boardData = mutableListOf<Triple<Int, Int, String>>()
    val boxData = mutableListOf<Pair<Int, Int>>()
    val targetData = mutableListOf<Pair<Int, Int>>()
    var playerPosX = -1
    var playerPosY = -1

    while (eventType != XmlPullParser.END_DOCUMENT) {
        when (eventType) {
            XmlPullParser.START_TAG -> {
                when (xmlParser.name) {
                    "name" -> worldName = xmlParser.nextText()
                    "rows" -> rows = xmlParser.nextText().toInt()
                    "cols" -> cols = xmlParser.nextText().toInt()
                    "box_color" -> boxColor = Color.parseColor(xmlParser.nextText())
                    "box_on_target_color" -> boxOnTargetColor =
                        Color.parseColor(xmlParser.nextText())
                    "target_color" -> targetColor = Color.parseColor(xmlParser.nextText())
                    "brick_color" -> brickColor = Color.parseColor(xmlParser.nextText())
                    "hall_color" -> hallColor = Color.parseColor(xmlParser.nextText())
                    "undefined_color" -> undefinedColor = Color.parseColor(xmlParser.nextText())
                    "player_color" -> playerColor = Color.parseColor(xmlParser.nextText())
                    "board" -> {
                        val posX = xmlParser.getAttributeValue(null, "posX").toInt()
                        val posY = xmlParser.getAttributeValue(null, "posY").toInt()
                        val type = xmlParser.nextText()
                        boardData.add(Triple(posX, posY, type))
                    }
                    "boxes" -> {
                        val posX = xmlParser.getAttributeValue(null, "posX").toInt()
                        val posY = xmlParser.getAttributeValue(null, "posY").toInt()
                        boxData.add(Pair(posX, posY))
                    }
                    "targets" -> {
                        val posX = xmlParser.getAttributeValue(null, "posX").toInt()
                        val posY = xmlParser.getAttributeValue(null, "posY").toInt()
                        targetData.add(Pair(posX, posY))
                    }
                    "player" -> {
                        playerPosX = xmlParser.getAttributeValue(null, "posX").toInt()
                        playerPosY = xmlParser.getAttributeValue(null, "posY").toInt()
                    }
                }
            }
        }
        //eventType = xmlParser.next()
    }
    // Calcular el tamaño de las celdas y del tablero
    val cellSize = min(canvas.width / cols, canvas.height / rows)
    val boardWidth = cellSize * cols
    val boardHeight = cellSize * rows
    val boardLeft = (canvas.width - boardWidth) / 2
    val boardTop = (canvas.height - boardHeight) / 2
    // Dibujar las celdas del tablero
    for (i in 0 until rows) {
        for (j in 0 until cols) {
            val left = boardLeft + j * cellSize
            val top = boardTop + i * cellSize
            val right = left + cellSize
            val bottom = top + cellSize
            val cellRect = Rect(left, top, right, bottom)
            val cellColor =
                when (boardData.firstOrNull { it.first == j && it.second == i }?.third) {
                    "X" -> brickColor
                    "O" -> hallColor
                    null -> undefinedColor
                    else -> throw IllegalStateException("Tipo de celda desconocido")
                }
            canvas.drawRect(cellRect, Paint().apply { color = cellColor })
        }
    }

    // Dibujar las cajas
    for (box in boxData) {
        val left = boardLeft + box.first * cellSize
        val top = boardTop + box.second * cellSize
        val right = left + cellSize
        val bottom = top + cellSize
        val boxRect = Rect(left, top, right, bottom)
        val boxColor = if (targetData.contains(box)) boxOnTargetColor else boxColor
        canvas.drawRect(boxRect, Paint().apply { color = boxColor })
    }

    // Dibujar los objetivos
    for (target in targetData) {
        val left = boardLeft + target.first * cellSize + cellSize / 4
        val top = boardTop + target.second * cellSize + cellSize / 4
        val right = left + cellSize / 2
        val bottom = top + cellSize / 2
        val targetRect = Rect(left, top, right, bottom)
        canvas.drawRect(targetRect, Paint().apply { color = targetColor })
    }

    // Dibujar el jugador
    if (playerPosX != -1 && playerPosY != -1) {
        val left = boardLeft + playerPosX * cellSize + cellSize / 4
        val top = boardTop + playerPosY * cellSize + cellSize / 4
        val right = left + cellSize / 2
        val bottom = top + cellSize / 2
        val playerRect = Rect(left, top, right, bottom)
        canvas.drawRect(playerRect, Paint().apply { color = playerColor })
    }
    */

}

// override fun onPostResume() {
//     super.onPostResume()
// surfaceView.post {
//      val canvas = surfaceHolder.lockCanvas()
// Llamar a la función drawBoxWorld con el objeto Canvas y los datos del mundo
// drawBoxWorld(canvas, "datos del mundo")
//  surfaceHolder.unlockCanvasAndPost(canvas)
// }
// }