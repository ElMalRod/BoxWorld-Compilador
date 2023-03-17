/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Analizadores.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elmalrod
 */
public class Lista {

    public TError primero;
    public int numElem;
    String xml;

    public Lista() {
        primero = null;
        numElem = 0;
    }

    public void insertar(String le, int li, int co, String t, String de) {

        if (numElem == 0) {
            TError nuevo = new TError(le, li, co, t, de);
            primero = nuevo;
            System.out.println(" 2. Inserto: " + nuevo.getLexema());
        } else {
            TError nuevo = new TError(le, li, co, t, de, primero);
            primero = nuevo;
        }
        numElem++;
        System.out.println("elementos" + numElem);

    }

    public String devolverXml() {
        return xml;
    }

    public void imprimir() {
        TError actual = primero;
        int cont = 0;
        StringBuilder sb = new StringBuilder();
        if (numElem == 1) {
            sb.append("<error>");
            sb.append("<lexema>" + actual.getLexema() + "</lexema>");
            sb.append("<line>" + actual.getLinea() + "</line>");
            sb.append("<column>" + actual.getColumna() + "</column>");
            sb.append("<type>" + actual.getTipo() + "</type>");
            sb.append("<description>" + actual.getDescripcion() + "</description>");
            sb.append("</error>");
            String xx = sb.toString();
            System.out.println("era el primero " + xx);
        }
        for (int i = 0; i < numElem; i++) {

            sb.append("<error>");
            sb.append("<lexema>" + actual.getLexema() + "</lexema>");
            sb.append("<line>" + actual.getLinea() + "</line>");
            sb.append("<column>" + actual.getColumna() + "</column>");
            sb.append("<type>" + actual.getTipo() + "</type>");
            sb.append("<description>" + actual.getDescripcion() + "</description>");
            sb.append("</error>");
            actual = actual.getSiguiente();
            String xx = sb.toString();
            cont++;
            System.out.println("cont: " + cont + "elementos: " + numElem);

            System.out.println("agregado :" + xx);
        }
        StringBuilder completo = new StringBuilder();
        completo.append("<errors>");
        completo.append(sb);
        completo.append("</errors>");
        xml = completo.toString();
        System.out.println("3:      --------" + xml);

        try (FileWriter fileWriter1 = new FileWriter(
                "/home/elmalrod/Documentos/Compiladores Primer Semestre/Box-World-JAVA/boxWorldServer/errores.xml")) {
            fileWriter1.write(xml);
        } catch (IOException ex) {
            Logger.getLogger(Lista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ver() {
        System.out.println("----------------------VIENDO--------------------------------");
        TError actual = primero;
        while (actual.getSiguiente() != null) {
            System.out.println("<error>");
            System.out.println("<lexema>" + actual.getLexema() + "</lexema>");
            System.out.println("<line>" + actual.getLinea() + "</line>");
            System.out.println("<type>" + actual.getTipo() + "</type>");
            System.out.println("<description>" + actual.getDescripcion() + "</description>");
            System.out.println("</error>");
            actual = actual.getSiguiente();
        }

    }

}
