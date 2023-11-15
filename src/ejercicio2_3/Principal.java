package ejercicio2_3;


import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author mario
 */
public class Principal {

    public static void main(String[] args) {
        BDManager bd = new BDManager();

        bd.abrirConexion();
        String opciones = """
                          1.Actualizar empleado
                          2.Crear proyecto
                          3.Consultar empleado
                          4.Consultar departemento
                          5.Consultar prollecto
                          6.Salir
                          """;
        Scanner sc = new Scanner(System.in);
        System.out.println(opciones);
        switch (sc.next()) {
            case "1":
                //Actualizar empleado
                System.out.println("Nombvre:");
                String nombre = sc.next();
                System.out.println("Ape1:");
                String ape1 = sc.next();
                System.out.println("ape2:");
                String ape2 = sc.next();
                System.out.println("Nss:");
                String nss = sc.next();
                Empregado emp = new Empregado(nombre, ape1, ape2, nss);
                bd.actuEmpregado(Integer.parseInt(nss), emp);
                break;
            case "2":
                //Crear proyecto
                System.out.println("Num proxecto:");
                int numProxec = Integer.parseInt(sc.next());
                System.out.println("Nome");
                String nombrePro = sc.next();
                System.out.println("Lugar");
                String lugar = sc.next();
                System.out.println("Num depa");
                int numDepa = Integer.parseInt(sc.next());
                Proxecto prx = new Proxecto(numProxec, numDepa, nombrePro, lugar);
                bd.insertarProxecto(prx);
                break;
            case "3":
                //Consultar empleado
                System.out.println("Nss:");
                 nss = sc.next();
                bd.busqEmpregado(nss);
                break;
            case "4":
                //Consultar departamento
                System.out.println("Num depa: ");
                numDepa = Integer.parseInt(sc.next());
                bd.consulDepa(numDepa);
                break;
            case "5":
                //Consultar prollecto
                System.out.println("Proyecto: ");
                int numPro = Integer.parseInt(sc.next());
                bd.consulPro(numPro);
                break;
            default:
                throw new AssertionError();
        }
        bd.cerrarConexion();
    }

}
