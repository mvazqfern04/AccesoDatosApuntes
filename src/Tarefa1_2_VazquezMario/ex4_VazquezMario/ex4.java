/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tarefa1_2_VazquezMario.ex4_VazquezMario;

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
public class ex4 {

    private File f;
    private ArrayList<Marca> marcas;

    public ex4() {
        this.f = new File("ficheros/coches.txt");
        this.marcas = new ArrayList();
        cargar();
    }

    private void cargar() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.f));

            String linea = br.readLine();
            while (!(linea == null)) {
                String nMarca = (String) linea.subSequence(0, linea.indexOf(" "));
                String nCoche = (String) linea.subSequence(linea.indexOf(" "), linea.length() );
                nCoche=nCoche.strip();
                Marca marcaN = new Marca(nMarca);

                if (!marcas.contains(marcaN)) {
                    marcas.add(marcaN);
                    marcaN.addCoche(nCoche);
                } else {
                    marcas.get(marcas.indexOf(marcaN)).addCoche(nCoche);
                }
                linea = br.readLine();
            }

        } catch (IOException ex) {
            System.err.println("No se puede leer");
        }

    }

    @Override
    public String toString() {
        String salida = "Ejercicio 4: \n";
        salida+="Marca:Modelos\n";
        for (Marca marca : marcas) {
            salida +=  marca.getNombre()+" ";
            for (String este : marca.getCoches()) {
                salida+=este+"/";
            }
            salida+="\n";
        }
        return salida;
    }

}
