/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TareaRepasoXML;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author node
 */
public class Pedido {

    private Integer id;
    private String nombre;
    private ArrayList<Producto> productos;

    public Pedido(Integer id, String nombre, ArrayList<Producto> productos) {
        this.id = id;
        this.nombre = nombre;
        this.productos = productos;
    }

    public Element toElement(Document doc) {
        Element salida = doc.createElement("pedido");
        Element eId = doc.createElement("id");
        eId.setTextContent(id.toString());
        
        salida.appendChild(eId);

        for (Producto producto : productos) {
            salida.appendChild(producto.toElement(doc));
        }

        return salida;
    }

    public void escribirEn(File fichero) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero, true));
            bw.write("Id: " + id.toString()+" Nombre: "+nombre);
            bw.newLine();
            for (Producto producto : productos) {
                bw.write(producto.toString());
            }
            bw.newLine();
            bw.close();
        } catch (IOException ex) {
        }

    }

    public String getNombre() {
        return nombre.trim();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
