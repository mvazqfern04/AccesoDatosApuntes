package tareas.ejercicio2_5;

import ejercicio2_4.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mario
 */
public class Proxecto {
    private Integer num_proxecto, num_departamento;
    private String nome_proxecto,lugar;

    public Proxecto(Integer num_proxecto, Integer num_departamento, String nome_proxecto, String lugar) {
        this.num_proxecto = num_proxecto;
        this.num_departamento = num_departamento;
        this.nome_proxecto = nome_proxecto;
        this.lugar = lugar;
    }

    public void setNum_proxecto(Integer num_proxecto) {
        this.num_proxecto = num_proxecto;
    }

    public void setNum_departamento(Integer num_departamento) {
        this.num_departamento = num_departamento;
    }

    public void setNome_proxecto(String nome_proxecto) {
        this.nome_proxecto = nome_proxecto;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    
    

    public Integer getNum_proxecto() {
        return num_proxecto;
    }

    public Integer getNum_departamento() {
        return num_departamento;
    }

    public String getNome_proxecto() {
        return nome_proxecto;
    }

    public String getLugar() {
        return lugar;
    }

    @Override
    public String toString() {
        return "Proxecto{" + "num_proxecto=" + num_proxecto + ", num_departamento=" + num_departamento + ", nome_proxecto=" + nome_proxecto + ", lugar=" + lugar + '}';
    }
    
    
    
}
