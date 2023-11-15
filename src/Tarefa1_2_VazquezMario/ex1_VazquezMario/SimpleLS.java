/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tarefa1_2_VazquezMario.ex1_VazquezMario;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author node
 */
public class SimpleLS {

    public static void  ls(String directorio){
        Path direc = Paths.get(directorio);
        
        System.out.println(direc);
        if (Files.isDirectory(direc)) {
            System.out.println("Es un directorio");
            try {DirectoryStream<Path> stream = Files.newDirectoryStream(direc);
            
                for(Path fichero: stream){
                    comprobarPermisos(fichero);
                    System.out.println(fichero.getFileName());
                }
            } catch (IOException|DirectoryIteratorException ex) {
                System.err.println(ex);
            }
        }
        
    }

    private static void comprobarPermisos(Path fichero) {
        if(fichero.toFile().isDirectory()){
            System.out.print("d");
        }else{
            System.out.print("-");
        }
        if(fichero.toFile().canRead()){
            System.out.print("r");
        }else{
            System.out.print("-");
        }
        if(fichero.toFile().canWrite()){
            System.out.print("w");
        }else{
            System.out.print("-");
        }
        if(fichero.toFile().canExecute()){
            System.out.print("x");
        }else{
            System.out.print("-");
        }
        System.out.print(" ");
    }
}
