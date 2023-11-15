/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tarefa1_2_VazquezMario.ex3_VaquezMario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author node
 */
public class ex3 {

    private String rC;
    private List<Alumno> als;
    private File f;

    public ex3(String f) {
        this.f = new File(f);
        this.als=new ArrayList();
        cargar();
    }

    private void cargar() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.f));

            this.rC = br.readLine();

            String sigui = br.readLine();
            String[] dividido;
            int i = 0;
            while (!(sigui==null)) {

                dividido = dividir(sigui);
                this.als.add(new Alumno(dividido[0], dividido[1], rC));
                i++;
                
                sigui = br.readLine();
            } ;
        } catch (IOException ex) {
            System.err.println("No se da leido");
        }
    }

    private String[] dividir(String ln) {
        int espacio = ln.indexOf(" ");
        String st = (String) ln.subSequence(0, espacio);
        String[] salida = {(String) ln.subSequence(0, espacio), (String) ln.subSequence(espacio+1, ln.length()-1)};
        return salida;
    }

    @Override
    public String toString() {
        return "ex3{" + "als=" + als + '}';
    }

   
    
    

}
