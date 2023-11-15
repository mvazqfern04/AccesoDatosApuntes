/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TareaRepasoXML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author node
 */
class Producto {

    private String id;
    private String nombre;
    private Integer precio;

    public Producto(String id, String nombre, Integer precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Element toElement(Document doc) {
        Element salida = doc.createElement("producto");

        Element eleId = doc.createElement("id");
        eleId.setTextContent(this.id);
        salida.appendChild(eleId);

        Element eleNom = doc.createElement("nombre");
        eleNom.setTextContent(this.nombre);
        salida.appendChild(eleNom);

        Element elePrecio = doc.createElement("precio");
        elePrecio.setTextContent(this.precio.toString());
        salida.appendChild(elePrecio);

        return salida;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        String salida = "";
        salida += "Id: " + this.id;
        salida += " Nombre: " + this.nombre;
        salida += " Precio: " + this.precio.toString() + "\n";
        return salida;
    }

}
