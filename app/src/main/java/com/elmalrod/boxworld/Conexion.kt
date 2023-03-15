package com.elmalrod.boxworld

import android.content.Context
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import java.io.*
import java.net.Socket

class Conexion(private val context: Context) {
    private val analista = Analista()
    fun enviarMensajes(mensajes: String) {
        val lineas = mensajes.split("\n") // Dividir mensajes en líneas separadas por '\n'

        SendMessageTask(context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, *lineas.toTypedArray())
    }


    private inner class SendMessageTask(private val context: Context) : AsyncTask<String, Void, Boolean>() {

        override fun doInBackground(vararg params: String): Boolean {
            try {
                val socket = Socket("192.168.0.35", 8080)
                val writer = PrintWriter(socket.getOutputStream(), true)

                for (mensaje in params) {
                    writer.println(mensaje)
                }
                writer.flush() // Flush the writer to ensure all data is sent
                socket.shutdownOutput()

                for (mensaje in params) {
                    val inputStream = socket.getInputStream()
                    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
                    val response = bufferedReader.readLine() // Read the response message
                    bufferedReader.close()
                    inputStream.close()

                    // Save response to a file
                    val file = File(context.filesDir, "errores.xml")
                    val fileWriter =
                        FileWriter(file, false) // Append to the file instead of overwriting it
                    fileWriter.write("$response\n")
                    fileWriter.close()
                    // Llamar a la función leerArchivo
                    //    analista.leerArchivo()
                    // Print the file path to the console
                    val filePath = file.absolutePath
                    println("File saved to: $filePath")

                    println("si llego el error: $response")
                }

                socket.close()
                return true
            } catch (e: Exception) {
                e.printStackTrace()
                return false
            }
        }


        override fun onPostExecute(result: Boolean) {
            if (!result) {
                Toast.makeText(context, "Mensaje Enviado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Error al enviar el mensaje", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}

