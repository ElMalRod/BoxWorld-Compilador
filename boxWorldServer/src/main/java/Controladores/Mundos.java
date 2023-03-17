/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

/**
 *
 * @author elmalrod
 */
public class Mundos {

    public String name, peti;

    public Mundos(String name, String petii) throws JSONException, IOException {
        this.name = name;
        this.peti = archivoXml(petii);
       
    }

    public String xmlMundo() {
        return "mundo devuelto";
    }

    static String archivoXml(String msj) throws JSONException, IOException {
        String jsonStr1 = msj;
        String jsonStr = "{world : " + jsonStr1 + " }";
        JSONObject json = new JSONObject(jsonStr);

        StringBuilder sb = new StringBuilder();

        sb.append(XML.toString(json));
        String xml = sb.toString();
        return xml;
        

    }
}
