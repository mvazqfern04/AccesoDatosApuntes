/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareas.ejercicio2_2.Entidades;

import java.util.Objects;

/**
 *
 * @author node
 */
public class Construccion {
    private Integer id;
    private String nombre;
    private float precio;
    private Integer id_granjero;

    public Construccion() {
    }

    public Construccion(Integer id, String nombre, float precio, Integer id_granjero) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.id_granjero = id_granjero;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Float.floatToIntBits(this.precio);
        hash = 37 * hash + Objects.hashCode(this.id_granjero);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Construccion other = (Construccion) obj;
        if (Float.floatToIntBits(this.precio) != Float.floatToIntBits(other.precio)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.id_granjero, other.id_granjero);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Integer getId_granjero() {
        return id_granjero;
    }

    public void setId_granjero(Integer id_granjero) {
        this.id_granjero = id_granjero;
    }
    
    
    
}
