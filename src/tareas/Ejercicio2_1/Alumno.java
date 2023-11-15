/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareas.Ejercicio2_1;

import java.util.Objects;

/**
 *
 * @author node
 */
public class Alumno {
    private String dni,nome,apllido,edad;

    public Alumno() {
    }

    public Alumno(String dni, String nome, String apllido, String edad) {
        this.dni = dni;
        this.nome = nome;
        this.apllido = apllido;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Alumno{" + "dni=" + dni + ", nome=" + nome + ", apllido=" + apllido + ", edad=" + edad + '}';
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApllido() {
        return apllido;
    }

    public void setApllido(String apllido) {
        this.apllido = apllido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.dni);
        hash = 73 * hash + Objects.hashCode(this.nome);
        hash = 73 * hash + Objects.hashCode(this.apllido);
        hash = 73 * hash + Objects.hashCode(this.edad);
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
        final Alumno other = (Alumno) obj;
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.apllido, other.apllido)) {
            return false;
        }
        return Objects.equals(this.edad, other.edad);
    }
    
    
}
