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
public class Tractor {

    private Integer id, velocidad, id_construccion;

    private enum TipoTractor {
        rural, urbano, cosechar, de_carreras, plantar
    }
    private TipoTractor tipo;
    private float precio_venta;
    private String proxima_cosecha;

    public Tractor() {
    }

    public Tractor(Integer id, Integer velocidad, Integer id_construccion, String tipoTractor, float precio_venta, String proxima_cosecha) {
        this.id = id;
        this.velocidad = velocidad;
        this.id_construccion = id_construccion;
        this.precio_venta = precio_venta;
        this.proxima_cosecha = proxima_cosecha;
        setTipoTractor(tipoTractor);
    }

    @Override
    public String toString() {
        return "Tractor{" + "id=" + id + ", velocidad=" + velocidad + ", id_constuccion=" + id_construccion + ", tipo=" + tipo + ", precio_venta=" + precio_venta + ", proxima_cosecha=" + proxima_cosecha + '}';
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

    public Integer getId_construccion() {
        return id_construccion;
    }

    public void setId_construccion(Integer id_construccion) {
        this.id_construccion = id_construccion;
    }

    public void setTipoTractor(String tipo) {
        if (tipo.equals(TipoTractor.cosechar.toString())) {
            this.tipo = TipoTractor.cosechar;
        } else if (tipo.equals(TipoTractor.de_carreras.toString())) {
            this.tipo = TipoTractor.de_carreras;
        } else if (tipo.equals(TipoTractor.plantar.toString())) {
            this.tipo = TipoTractor.plantar;
        } else if (tipo.equals(TipoTractor.rural.toString())) {
            this.tipo = TipoTractor.rural;
        } else {
            this.tipo = TipoTractor.urbano;
        }
    }
    
    public String getTipoTractor(){
        return tipo.toString();
    }

    public float getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(float precio_venta) {
        this.precio_venta = precio_venta;
    }

    public String getProxima_cosecha() {
        return proxima_cosecha;
    }

    public void setProxima_cosecha(String proxima_cosecha) {
        this.proxima_cosecha = proxima_cosecha;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.id);
        hash = 31 * hash + Objects.hashCode(this.velocidad);
        hash = 31 * hash + Objects.hashCode(this.id_construccion);
        hash = 31 * hash + Objects.hashCode(this.tipo);
        hash = 31 * hash + Float.floatToIntBits(this.precio_venta);
        hash = 31 * hash + Objects.hashCode(this.proxima_cosecha);
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
        final Tractor other = (Tractor) obj;
        if (Float.floatToIntBits(this.precio_venta) != Float.floatToIntBits(other.precio_venta)) {
            return false;
        }
        if (!Objects.equals(this.proxima_cosecha, other.proxima_cosecha)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.velocidad, other.velocidad)) {
            return false;
        }
        if (!Objects.equals(this.id_construccion, other.id_construccion)) {
            return false;
        }
        return this.tipo == other.tipo;
    }

    

}
