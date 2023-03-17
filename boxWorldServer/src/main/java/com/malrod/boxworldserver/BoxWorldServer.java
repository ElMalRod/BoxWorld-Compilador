/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.malrod.boxworldserver;

import Controladores.Lista;
import Analizadores.*;
import Controladores.Mundos;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import interfaz.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.json.*;

/**
 *
 * @author elmalrod
 */
public class BoxWorldServer {

    static String peti;
    static ServerJFrame sjf = new ServerJFrame();
    static List<JSONObject> arrayWorlds = new ArrayList<>(); // Lista que almacena todos los mundos
    static List<Mundos> wordlsXml =new ArrayList<>(); 
    static Lista lista = new Lista();
    static String xml, name, all, name2, mundo;
    static boolean aux = false;
    static boolean aux1 = false; //peticion todos los mundos
    static boolean aux2 = false; //peticion todos un mundo
    

    private static final Logger logger = LoggerFactory.getLogger(BoxWorldServer.class);

    public static void main(String[] args) throws IOException, JSONException {
        sjf.setVisible(true);

        logger.info("Starting server...");
        sjf.agregarLog("Iniciando servidor"+ "\n");

        ServerSocket serverSocket = new ServerSocket(8080);
        logger.info("Server started on port {}", serverSocket.getLocalPort());

         while (true) {
            Socket clientSocket = serverSocket.accept();
            logger.info("Accepted connection from {}", clientSocket.getInetAddress().getHostAddress());
            sjf.agregarLog("Se acepto conexion"+ "\n");
            
             // Request data
        DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

        // Read data
        DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
             // Create a PrintWriter and write output data
            PrintWriter printWriter = new PrintWriter(outputStream, true);

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            

            StringBuilder messageBuilder = new StringBuilder(); // Usamos un StringBuilder para concatenar todas las
            // líneas
            String line;
            while ((line = reader.readLine()) != null) { // Leer todas las líneas disponibles
                messageBuilder.append(line);
                messageBuilder.append("\n"); // Agregar el carácter '\n' de vuelta para separar las líneas
            }
            String message = messageBuilder.toString(); // Convertir el StringBuilder a una cadena

            logger.info("Received message from {}: {}", clientSocket.getInetAddress().getHostAddress(), message + "\n");
            peti = message;
            sjf.agregarLog(peti);

            // Escribimos el JSON a un archivo
            File file = new File("/home/elmalrod/Documentos/Compiladores Primer Semestre/Box-World-JAVA/boxWorldServer/archivo.json");
            PrintWriter fileWriter = new PrintWriter(file);
            fileWriter.write(message);
            fileWriter.close();

            // analizar
            sjf.agregarLog("Se mando nuevo mensaje"+ "\n");
            aux = false;
            aux1 = false;
            aux2= false;
            lista = new Lista(); // reiniciar lista de errores
            analizarMensaje();
            System.out.println("XML AHORA ES: " + xml);
           

            if (aux == true) {
                printWriter.println(xml + "\n");
            } 
            //todos los mundos
             if (aux1== true) {
                 //mandar todos los mundos
                 System.out.println("MAMNDA LOS MUNDOS");
                 mostrarMundos();
                 printWriter.println(all + "\n");
                 
             }
             //mundo en especifico
             //todos los mundos
             if (aux2== true) {
                 //mandar un solo mundo
                 System.out.println("MANDA 1 MUNDO");
                 buscarMundo();
                 printWriter.println(mundo+ "\n");
                 
                 
             }
            printWriter.flush(); // flush the PrintWriter to ensure all data is sent
            printWriter.close();

        }
    }

    static void analizarMensaje() throws FileNotFoundException, JSONException, IOException {
        Lexer lexico = new Lexer(new FileReader(
                "/home/elmalrod/Documentos/Compiladores Primer Semestre/Box-World-JAVA/boxWorldServer/archivo.json"));
        parser sintactico = new parser(lexico);
        try {
            sintactico.parse();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(BoxWorldServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        // verificar si hay errores
        if (aux == false) {
            
            if(aux1==false && aux2==false){
                sjf.agregarLog("No se encontraron errores");
            archivoXml(peti);
            //guardar mundo
            Mundos nuevo = new Mundos(name, peti);
            System.out.println("NOMBRESS: "+ name );
            wordlsXml.add(nuevo);
            imprimir();
            }
            
            

        } else {
            System.out.println("encontro errores!!!!!!");
            lista.imprimir();
            xml = lista.devolverXml();
            sjf.agregarXml(xml);
            
        }
        
        


    }

    public static void noMistakes(boolean c) {
        aux = c;
        sjf.agregarLog("Se encontro errores");
        lista.imprimir();
    }
    
    public static void  peticionMundos(boolean c)
    {
        System.out.println("Mando peticion mundos");
        aux1 = true;
        
    }
    //funcion que guarda el nombre de los mundos
    public static void saveName(String name1)
    {
        name = name1;
        
    }
    public static void peticionMundo(String name1)
    {
        aux2= true;
        name2 = name1;
        System.out.println("Manda peticion mundo especifico");
        
    }
    public void tablaErrores(String le, int li, int co, String t, String de) {
        System.out.println("1. Mandooooo " + le + li + co + t + de);
        lista.insertar(le, li, co, t, de);
        
        lista.ver();

    }
    public static void buscarMundo()
    {
        for (Mundos elemento: wordlsXml) {
            if (name2.equals(elemento.name)) {
                mundo = elemento.peti;
                System.out.println("MUNDOO------------------- "+ elemento.peti);
            }
        }
    }
    static void mostrarMundos()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<worlds>");
        for (Mundos elemento: wordlsXml) {
            sb.append("<world>");
            sb.append(elemento.name);
            sb.append("</world>");

        }
        sb.append("</worlds>");
        all= sb.toString();
        System.out.println("MUNDOS "+ all);
    }
    static void imprimir()
    {
        
        for (Mundos elemento: wordlsXml) {
            System.out.println("NAME: "+elemento.name +"XML: "+ elemento.peti);

        }
    }
   

    static void archivoXml(String msj) throws JSONException, IOException {
        String jsonStr1 = msj;
        String jsonStr = "{world : " + jsonStr1 + " }";
        JSONObject json = new JSONObject(jsonStr);
        arrayWorlds.add(json);

        StringBuilder sb = new StringBuilder();
        sb.append("<worlds>");
        for (JSONObject world : arrayWorlds) {
            sb.append(XML.toString(world));
        }
        sb.append("</worlds>");
        xml = sb.toString();

        System.out.println(xml);

        try (FileWriter fileWriter1 = new FileWriter(
                "/home/elmalrod/Documentos/Compiladores Primer Semestre/Box-World-JAVA/boxWorldServer/sokoban.xml")) {
            fileWriter1.write(xml);
        }
        sjf.agregarXml(xml);

    }

}
