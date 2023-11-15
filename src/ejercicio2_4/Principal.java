package ejercicio2_4;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        String opciones2 = """
                           1.Actualizar salario según nombre depar
                           2.Insertar departamento
                           3.Eliminar empleado de proyecto
                           4.Mostrar empregado por localidade
                           5.Cambiar departamentno de un proxecto
                           6.Insertar proxecto
                           7.Borrar proxecto
                           8.Mostrar proxctos según departamento
                           9.Modigicar direccion empleado
                           10.Consulatr proxecto
                           11.Consultar depas con mas de x proyectos
                           12.Contar empleados de un departamento
                           13.Insertar proxecto
                           14.incrementarSueldoDepa
                           15.verDeFormaEnrevesada
                          """;
        Scanner sc = new Scanner(System.in);
        System.out.println(opciones2);
//        ejer2_3(sc, bd);
        switch (sc.next()) {
            case "1":
                System.out.println("Nombre departamento, en maysculas:");
                String nomDepa = sc.next();
                System.out.println("Cantidad: ");
                String canti = sc.next();
                bd.aumentarSalario(canti, nomDepa);
                break;
            case "2":
                //String num, String nomDepa, String nssDirector
                System.out.println("Numero depar:");
                String num = sc.next();
                System.out.println("Nombre deapar:");
                String nom = sc.next();
                System.out.println("Nss del director:");
                String nssDir = sc.next();
                bd.inserDepa(num, nom, nssDir);
                break;
            case "3":
                //Borrar empregado de un proxecto (nss emprgado, cod proxecto)
                System.out.println("Nss empleado: ");
                String nss = sc.next();
                System.out.println("Codiogo de proxecto:");
                String cod = sc.next();
                try {
                    bd.quitarEmpregadoProxecto(nss, cod);
                } catch (Exception e) {
                    System.err.println("No existe eso");
                }
                break;
            case "4":
                //Mostar empregados por localidad
                System.out.println("Su localidad:");
                String local = sc.next();
                bd.mostarLocali(local);
                break;
            case "5":
                //Cambiar depa de proxecto 
                System.out.println("Nombre proxecto: ");
                String nomPro = sc.next();
                //PORTAL
                System.out.println("Nome depa: ");
                String nomeDepa = sc.next();
                //PERSOAL  CONTABILIDADE
                bd.changeDepaFromProxecto(nomeDepa, nomPro);
                break;
            case "6":
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
            case "7":
                //Borrar proxecto 
                System.out.println("Numero de proxecto: ");
                int numProxe = Integer.parseInt(sc.next());
                bd.borrarProxecto(numProxe);
                break;
            case "8":
                //Mostrar proxectos según nombre de depa
                System.out.println("Nome depa:");
                String noDepa = sc.next();
                System.out.println(bd.obtenerProxecto(noDepa).toString());
                break;
            case "9":
                //Cambair direccio
                System.out.println("Nss:");
                nss = sc.next();
                System.out.println("Rua: ");
                String rua = sc.next();
                System.out.println("Numero rua:");
                int numRua = Integer.parseInt(sc.next());
                System.out.println("piso:");
                String piso = sc.next();
                System.out.println("Cp: ");
                int cp = Integer.parseInt(sc.next());
                System.out.println("Localidad:");
                String locali = sc.next();
                
                bd.sp_cambioDomicilio(nss,rua,numRua,piso,cp,locali);
                
                break;
            case "10":
                //Consultar proxecto
                
                System.out.println("Numero de proxecto:");
                int numProx = Integer.parseInt(sc.next());

                bd.proceDatosProxecto(numProx);
                
                break;
            case "11":
                //Mostrar departa que controlen mas de x proyectos
                
                System.out.println("Cantidad de proyectos:");
                int cant = Integer.parseInt(sc.next());
                
                bd.comprobarCantidadProyectosDeDepas(cant);
                
                break;
            case "12":
                //Funcion consigue nome depa y devuelve num de empregados
                System.out.println("Nombre depa:");
                nomDepa = sc.next();
                
                bd.contarEmpregadosPorDepa(nomDepa);
            case "13":
                System.out.println("Numero proxecto:");
                int numPro = Integer.parseInt(sc.next());
                System.out.println("Numero departamento:");
                int numDep = Integer.parseInt(sc.next());
                System.out.println("Nome proxecto:");
                nomPro = sc.next();
                System.out.println("Lugar proxecto:");
                lugar = sc.next();
                bd.insetao(new Proxecto(numPro,numDep,nomPro,lugar));
                break;
            case "14":
                System.out.println("Cantidad a incrementar:");
                Integer incre = Integer.parseInt(sc.next());
                System.out.println("Numero de departamento: ");
                Integer nuDep = Integer.parseInt(sc.next());
                bd.incrementarSueldoDepa(incre,nuDep);
                break;
            case "15":
                System.out.println("Minimo de proxectos?: ");
                int i= Integer.parseInt(sc.next());
                bd.verDeFormaEnrevesada(i);
                break;
            default:
                throw new AssertionError();
        }
        bd.cerrarConexion();
    }

    private static void ejer2_3(Scanner sc, BDManager bd) throws AssertionError, NumberFormatException {
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
    }

}
