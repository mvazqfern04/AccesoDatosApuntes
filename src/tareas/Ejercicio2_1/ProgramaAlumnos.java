/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareas.Ejercicio2_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author node
 */
public class ProgramaAlumnos {
    
    public static void main(String[] args) {
        
        OperacionDB oDB = new OperacionDB();
        
        System.out.println(oDB.abrirConexion());
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Que desea hacer?: ");
        String eleccion = "0";
        while (!(Integer.parseInt(eleccion) >= 6)) {
            System.out.println("1-Listar\n"
                    + "2-Agregar alumno\n"
                    + "3-Eleiminar alumno\n"
                    + "4-Buscar alumno\n"
                    + "5-Modificar alumno\n"
                    + "6-Salir\n");
            eleccion = sc.next();
            switch (eleccion) {
                case "1":
                    oDB.listadoAlumno();
                    break;
                case "2":
                    System.out.print("Id: ");
                    String id = sc.next();
                    System.out.print("Nombre: ");
                    String nombre = sc.next();
                    System.out.print("Apellido: ");
                    String apellido = sc.next();
                    System.out.print("Edad: ");
                    String edad = sc.next();
                    oDB.anadeAlumno(id, nombre, apellido, edad);
                    break;
                case "3":
                    System.out.print("Id del alumno: ");
                    String idBorrar = sc.next();
                    oDB.borrarAlumno(idBorrar);
                    break;
                case "4":
                    System.out.print("Id del alumno: ");
                    String idBusqueda = sc.next();
                    oDB.consultaAlumno(idBusqueda);
                    break;
                case "5":
                    System.out.print("Id del alumno: ");
                    id = sc.next();
                    oDB.consultaAlumno(id);
                    
                    System.out.print("Nombre: ");
                    nombre = sc.next();
                    System.out.print("Apellido: ");
                    apellido = sc.next();
                    System.out.print("Edad: ");
                    edad = sc.next();
                    
                    oDB.modificaAlumno(id, nombre, apellido, edad);
                    break;
                default:
                    break;
            }
        }
        oDB.cerrarConexion();
    }
    
}
