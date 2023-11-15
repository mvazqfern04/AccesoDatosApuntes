/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tarefa1_2_VazquezMario.ex6_VazquezMario;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author node
 */
public class lsR {


    public lsR() {
    }

    public String ejecutar(String ruta) {
        String salida = "";
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(this.ruta));
            
            try {
                DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(ruta));
                
                for (Path path : stream) {
                    salida+=lsPermisos(path);
                    if(path.toFile().isDirectory()){
                        salida+="\t"+ejecutar(path.toFile().toString());
                    }
                    
                }
            } catch (IOException ex) {
                System.err.println("No va");
            }

//        } catch (FileNotFoundException ex) {
//            System.err.println("No se encontro archivo");
//        }
        return salida;
    }

    public String lsPermisos(Path ruta) {
        String salida = "";
        salida += (ruta.toFile().isDirectory()) ? "d" : "-";
        salida += (ruta.toFile().canRead()) ? "r" : "-";
        salida += (ruta.toFile().canWrite()) ? "w" : "-";
        salida += (ruta.toFile().canExecute()) ? "x" : "-";
        salida += " " + ruta.getFileName().toString() + "\n";
        return salida;
    }

//    public List<Path> ficherosContenidos(Path ruta) {
//        ArrayList<Path> salida = new ArrayList();
//        if (ruta.toFile().isDirectory()) {
//            ruta.get
//        }
//        return salida;
//    }
}
