/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareas.ejercicio2_5;

import ejercicio2_4.*;
import java.util.Date;

/**
 *
 * @author mario
 */
public class Empregado {
    private String nombre,apelido_1,apelido_2,nss,rua,piso,cp,localidade,sexo,nss_supervisa;
    private Integer numero_rua,num_departamento_pertenece;
    private Float salario;
    private Date data_nacemento;

    public Empregado(String nombre, String apelido_1, String apelido_2, String nss) {
        this.nombre = nombre;
        this.apelido_1 = apelido_1;
        this.apelido_2 = apelido_2;
        this.nss = nss;
    }

    public Empregado(String nombre, String apelido_1, String apelido_2, String nss, String rua, String piso, String cp, String localidade, String sexo, String nss_supervisa, Integer numero_rua, Integer num_departamento_pertenece, Float salario, Date data_nacemento) {
        this.nombre = nombre;
        this.apelido_1 = apelido_1;
        this.apelido_2 = apelido_2;
        this.nss = nss;
        this.rua = rua;
        this.piso = piso;
        this.cp = cp;
        this.localidade = localidade;
        this.sexo = sexo;
        this.nss_supervisa = nss_supervisa;
        this.numero_rua = numero_rua;
        this.num_departamento_pertenece = num_departamento_pertenece;
        this.salario = salario;
        this.data_nacemento = data_nacemento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApelido_1() {
        return apelido_1;
    }

    public String getApelido_2() {
        return apelido_2;
    }

    public String getNss() {
        return nss;
    }

    public String getRua() {
        return rua;
    }

    public String getPiso() {
        return piso;
    }

    public String getCp() {
        return cp;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getSexo() {
        return sexo;
    }

    public String getNss_supervisa() {
        return nss_supervisa;
    }

    public Integer getNumero_rua() {
        return numero_rua;
    }

    public Integer getNum_departamento_pertenece() {
        return num_departamento_pertenece;
    }

    public Float getSalario() {
        return salario;
    }

    public Date getData_nacemento() {
        return data_nacemento;
    }

    @Override
    public String toString() {
        return "Empregado{" + "nombre=" + nombre + ", apelido_1=" + apelido_1 + ", apelido_2=" + apelido_2 + ", nss=" + nss + ", rua=" + rua + ", piso=" + piso + ", cp=" + cp + ", localidade=" + localidade + ", sexo=" + sexo + ", nss_supervisa=" + nss_supervisa + ", numero_rua=" + numero_rua + ", num_departamento_pertenece=" + num_departamento_pertenece + ", salario=" + salario + ", data_nacemento=" + data_nacemento + '}';
    }
    
    
    
    
}
