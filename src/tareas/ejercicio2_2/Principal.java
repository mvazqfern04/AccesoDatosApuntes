/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareas.ejercicio2_2;

import java.util.Scanner;
import tareas.ejercicio2_2.BDManager.BDManager;
import tareas.ejercicio2_2.Entidades.Construccion;
import tareas.ejercicio2_2.Entidades.Granjero;
import tareas.ejercicio2_2.Entidades.Tractor;

/**
 *
 * @author node
 */
public class Principal {

    public static void main(String[] args) {
        BDManager bd = new BDManager();

        bd.abrirConexion();

        //1.Crear2.Modificar 3Eliminar. 4.Consultar 5.Salir
        String qHacer = """
                        1.Crear
                        2.Modificar
                        3.Eliminar.
                        4.Consultar
                        5.Parte 2
                        6.Salir""";
        String qTabla = """
                        1.GRANJERO
                        2.CONSTRUCCION
                        3.TRACTOR
                        4.SALIR""";
        Scanner sc = new Scanner(System.in);
        System.out.println("Que desa hacer?");
        System.out.println(qHacer);
        String respuesta = sc.next();
        while (Integer.parseInt(respuesta) < 6) {
            switch (respuesta) {
                case "1":
                    System.out.println("Que tabla?: ");
                    System.out.println(qTabla);
                    respuesta = sc.next();
                    insertar(respuesta, bd, sc);
                    break;
                case "2":
                    System.out.println("Que tabla:");
                    System.out.println(qTabla);
                    respuesta = sc.next();
                    modificar(respuesta, bd, sc);

                    break;
                case "3":
                    System.out.println("Que tabla:");
                    System.out.println(qTabla);
                    respuesta = sc.next();
                    eliminar(respuesta, bd, sc);
                    break;
                case "4":
                    System.out.println("Que tabla:");
                    System.out.println(qTabla);
                    respuesta = sc.next();
                    ver(respuesta, bd, sc);
                    break;
                case "5":
                    System.out.println("Que deseas hacer?: ");
                    String qHacerP2="""
                                    1.Construcciones de un granjero.
                                    2.Subir precio rural 10%
                                    3.Tipo construccines de tractor
                                    4.Lista plantaciones de cada granjero
                                    """;
                    System.out.println(qHacerP2);
                    respuesta = sc.next();
                    hacerEjercicio2(respuesta, bd, sc);
                    break;
                default:
                    break;
            }
            System.out.println(qHacer);
            respuesta = sc.next();
        }

        bd.cerrarConexion();

    }

    //Metodods encapsulados
    private static void insertar(String respuesta, BDManager bd, Scanner sc) {
        switch (respuesta) {
            case "1":
                //Granjero
                //id, nombre, descripcion, dinero, puntos, nivel
                Granjero gr = new Granjero();
                System.out.println("ID:");
                gr.setId(Integer.parseInt(sc.next()));
                System.out.println("Nombre:");
                gr.setNombre(sc.next());
                System.out.println("Descripcion:");
                gr.setDescripcion(sc.next());
                System.out.println("Dinero:");
                gr.setDinero(Float.parseFloat(sc.next()));
                System.out.println("Puntos:");
                gr.setPuntos(Integer.parseInt(sc.next()));
                System.out.println("Nivel: ");
                gr.setNivel(Integer.parseInt(sc.next()));
                bd.crearGranjero(gr);

                break;
            case "2":
                //Construccion
                //id, nombre, precio, id_granjero
                Construccion cons = new Construccion();
                System.out.println("ID: ");
                cons.setId(Integer.parseInt(sc.next()));
                System.out.println("Nombre: ");
                cons.setNombre(sc.next());
                System.out.println("Precio: ");
                cons.setPrecio(Float.parseFloat(sc.next()));
                System.out.println("Id granjero: ");
                cons.setId_granjero(Integer.parseInt(sc.next()));
                bd.crearConstrucion(cons);

                break;
            case "3":
                Tractor tractor = new Tractor();
                System.out.println("ID:");
                tractor.setId(Integer.parseInt(sc.next()));
                System.out.println("Id granjero:");
                tractor.setId_construccion(Integer.parseInt(sc.next()));
                System.out.println("Precio venta:");
                tractor.setPrecio_venta(Float.parseFloat(sc.next()));
                System.out.println("Proxima cosecha:");
                tractor.setProxima_cosecha(sc.next());
                System.out.println("Tipo tractor(rural, urbano, cosechar, de_carreras, plantar):");
                tractor.setTipoTractor(sc.next());
                System.out.println("Velocidad: ");
                tractor.setVelocidad(Integer.parseInt(sc.next()));
                bd.crearTractor(tractor);
                break;
            default:
                throw new AssertionError();
        }
    }

    private static void modificar(String respuesta, BDManager bd, Scanner sc) {
        switch (respuesta) {
            case "1":
                //Granjero
                //id, nombre, descripcion, dinero, puntos, nivel
                Granjero gr = new Granjero();
                System.out.println("Cual, id?:");
                gr.setId(Integer.parseInt(sc.next()));
                System.out.println("nombre:");
                gr.setNombre(sc.next());
                System.out.println("Decripcion:");
                gr.setDescripcion(sc.next());
                System.out.println("dinero:");
                gr.setDinero(Float.parseFloat(sc.next()));
                System.out.println("puntos:");
                gr.setPuntos(Integer.parseInt(sc.next()));
                System.out.println("Nivel:");
                gr.setNivel(Integer.parseInt(sc.next()));
                bd.modificarGranjero(gr);

                break;
            case "2":
                //Construccion
                //id, nombre, precio, id_granjero
                Construccion cons = new Construccion();
                System.out.println("ID: ");
                cons.setId(Integer.parseInt(sc.next()));
                System.out.println("Nombre: ");
                cons.setNombre(sc.next());
                System.out.println("Precio: ");
                cons.setPrecio(Float.parseFloat(sc.next()));
                System.out.println("Id granjero: ");
                cons.setId_granjero(Integer.parseInt(sc.next()));
                bd.modificarConstruccion(cons);

                break;
            case "3":
                Tractor tractor = new Tractor();
                System.out.println("Cual es su ID?:");
                tractor.setId(Integer.parseInt(sc.next()));
                System.out.println("Id construccion:");
                tractor.setId_construccion(Integer.parseInt(sc.next()));
                System.out.println("Precio venta:");
                tractor.setPrecio_venta(Float.parseFloat(sc.next()));
                System.out.println("Proxima cosecha:");
                tractor.setProxima_cosecha(sc.next());
                System.out.println("Tipo tractor(rural, urbano, cosechar, de_carreras, plantar):");
                tractor.setTipoTractor(sc.next());
                System.out.println("Velocidad: ");
                tractor.setVelocidad(Integer.parseInt(sc.next()));
                bd.modificarTractor(tractor);
                break;
            default:
                throw new AssertionError();
        }
    }

    private static void eliminar(String respuesta, BDManager bd, Scanner sc) {
        switch (respuesta) {
            case "1":
                //Granjero
                //id, nombre, descripcion, dinero, puntos, nivel
                Granjero gr = new Granjero();
                System.out.println("Cual, id?:");
                gr.setId(Integer.parseInt(sc.next()));

                bd.eliminarGranjero(gr);

                break;
            case "2":
                //Construccion
                //id, nombre, precio, id_granjero
                Construccion cons = new Construccion();
                System.out.println("ID: ");
                cons.setId(Integer.parseInt(sc.next()));

                bd.eliminarConstruccion(cons);

                break;
            case "3":
                Tractor tractor = new Tractor();
                System.out.println("Cual es su ID?:");
                tractor.setId(Integer.parseInt(sc.next()));
                bd.eliminarTractor(tractor);
                break;
            default:
                throw new AssertionError();
        }
    }

    private static void ver(String respuesta, BDManager bd, Scanner sc) {
        switch (respuesta) {
            case "1":
                //Granjero
                //id, nombre, descripcion, dinero, puntos, nivel
                System.out.println("ID:");
                Granjero gr = bd.verGranjero(Integer.parseInt(sc.next()));
                System.out.println(gr.toString());
                break;
            case "2":
                //Construccion
                //id, nombre, precio, id_granjero
                System.out.println("ID:");
                Construccion c = bd.verConstruccion(Integer.parseInt(sc.next()));
                System.out.println(c.toString());

                break;
            case "3":
                System.out.println("ID:");
                Tractor tr = bd.verTractor(Integer.parseInt(sc.next()));
                System.out.println(tr.toString());
                break;
            default:
                throw new AssertionError();
        }
    }

    private static void hacerEjercicio2(String respuesta, BDManager bd, Scanner sc) {
        switch (respuesta) {
            case "1":
                System.out.println("Id:");
                bd.contruccionesDeGranjero(Integer.parseInt(sc.next()));
                break;
            case "2":
                System.out.println("");
                bd.aumentarPrecioTractoresRurales();
                break;
            case "3":
                bd.listarTractorYSusConstruccines();
                System.out.println("\n");
                break;
            case "4":
                bd.listarPlantaciones();
                break;
            default:
                break;
        }
    }

}
