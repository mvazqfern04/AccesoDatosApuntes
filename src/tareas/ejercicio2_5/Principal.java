/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareas.ejercicio2_5;

import java.util.Scanner;

/**
 *
 * @author node
 */
public class Principal {

    public static void main(String[] args) {
        String opciones = """
                          1.Obtener informacion sobre SXBD
                          2.Todas as taboas
                          3.Ver columnas
                          4.Ver procedimientos
                          5.Primary Key de la tabla
                          6.Ver claves foraneas
                          7.Ejercicio 3
                          8.Limites puestos por el conector
                          9.Informacion sobre tranacciones
                          10.Soporte de caracteristicas
                          11.Metadatos de una consulta
                          """;
        Scanner sc = new Scanner(System.in);
        
        BDManager bd = new BDManager();
        bd.abrirConexion();
        
        System.out.println(opciones+"\n Que desea hacer");
        switch (sc.next()) {
            case "1":
                bd.mostarCosasSXBD();
                break;
            case "2":
                bd.mostarInfoAllTablas();
                break;
            case "3":
                System.out.println("Esquema: ");
                String esquema = sc.next();
                System.out.println("Tabla: ");
                String tabla = sc.next();
                bd.mostraColumnas(esquema, tabla);
                break;
            case "4":
                bd.verProcedimentos();
                break;
            case "5":
                System.out.println("Nombre tabla: ");
                String nom= sc.next();
                System.out.println("Esquema: ");
                String esquem = sc.next();
                bd.mostrarPrimaryKey(nom,esquem);
                break;
            case "6":
                System.out.println("Nombre tabla: ");
                nom = sc.next();
                System.out.println("Esquema: ");
                esquem = sc.next();
                bd.clavesForaneas(nom,esquem);
                break;
            case "7":
                bd.ejercicio3();
                break;
            case "8":
                bd.limitesDelConectos();
                break;
            case "9":
                bd.infoTransAcciones();
                break;
            case "10":
                bd.soporteCaracteristicas();
                break;
            case "11":
                //No funciona el *
                System.out.println("Consulta: ");
                sc.nextLine();
                String consulta=sc.nextLine();
                System.out.println(consulta);
                bd.parametrosDeConsulta(consulta);
                break;
            default:
                break;
        }

        bd.cerrarConexion();
    }
}
