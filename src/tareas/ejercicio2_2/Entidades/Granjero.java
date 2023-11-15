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
public class Granjero {
    private Integer id,puntos,nivel;
    private String nombre,descripcion;
    private float dinero;

    public Granjero() {
    }

    public Granjero(Integer id, Integer puntos, Integer nivel, String nombre, String descripcion, float dinero) {
        this.id = id;
        this.puntos = puntos;
        this.nivel = nivel;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dinero = dinero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getDinero() {
        return dinero;
    }

    public void setDinero(float dinero) {
        this.dinero = dinero;
    }

    @Override
    public String toString() {
        return "Granjero{" + "id=" + id + ", puntos=" + puntos + ", nivel=" + nivel + ", nombre=" + nombre + ", descripcion=" + descripcion + ", dinero=" + dinero + '}';
    }

}
