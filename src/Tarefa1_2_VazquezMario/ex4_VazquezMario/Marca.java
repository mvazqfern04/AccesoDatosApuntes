/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tarefa1_2_VazquezMario.ex4_VazquezMario;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author node
 */
public class Marca {
    private String nombre;
    private List<String> coches;

    public Marca(String m) {
        this.nombre=m;
        this.coches = new ArrayList();
    }
    
    public void addCoche(String co){
        coches.add(co);
    }

    public String getNombre() {
        return nombre;
    }

    public List<String> getCoches() {
        return coches;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.nombre);
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
        final Marca other = (Marca) obj;
        return Objects.equals(this.nombre, other.nombre);
    }

    @Override
    public String toString() {
        return "Marca{" + "nombre=" + nombre + ", coches=" + coches + '}';
    }
    
    
    
    
}
