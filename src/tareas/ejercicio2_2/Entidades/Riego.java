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
public class Riego {
    private Integer id,velocidad,id_plantacion;
    private String tipo;

    public Riego() {
    }

    public Riego(Integer id, Integer velocidad, Integer id_plantacion, String tipo) {
        this.id = id;
        this.velocidad = velocidad;
        this.id_plantacion = id_plantacion;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Integer velocidad) {
        this.velocidad = velocidad;
    }

    public Integer getId_plantacion() {
        return id_plantacion;
    }

    public void setId_plantacion(Integer id_plantacion) {
        this.id_plantacion = id_plantacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.velocidad);
        hash = 97 * hash + Objects.hashCode(this.id_plantacion);
        hash = 97 * hash + Objects.hashCode(this.tipo);
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
        final Riego other = (Riego) obj;
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.velocidad, other.velocidad)) {
            return false;
        }
        return Objects.equals(this.id_plantacion, other.id_plantacion);
    }
    
    
    
}
