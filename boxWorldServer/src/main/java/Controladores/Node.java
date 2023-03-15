/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;
import Analizadores.*;

/**
 *
 * @author elmalrod
 */
public class Node {
    public TError error;
    public Node next;

    public Node(TError error) {
        this.error = error;
        next = null;
    }
}
