/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareas.Ejercicio2_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author node
 */
public class OperacionDB {

    private Connection con;
    private Statement stmt;

    //Alumno(String dni, String nombre, String apllido, String edad)
    //DB -> colegio
    //Tabla -> alumnos
    public boolean abrirConexion() {
        //TO-DO
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.err.println("Excepcionn: " + e.toString());
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/colegio", "root", "abc123.");
            stmt = con.createStatement();

            stmt.execute("create DATABASE if NOT EXISTS Colegio");

            String crearTabla = "CREATE TABLE IF NOT EXISTS alumnos (dni VARCHAR(9) not NULL"
                    + ", nombre VARCHAR(255)"
                    + ", apellido VARCHAR(255)"
                    + ", edad INTEGER(4)"
                    + ", PRIMARY KEY ( dni ))";

            stmt.executeUpdate(crearTabla);
            return true;

        } catch (SQLException ex) {
            try {
                System.err.println("Error con la clase Connection");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "abc123.");
                stmt = con.createStatement();
                
                stmt.execute("create DATABASE if NOT EXISTS Colegio");
                return false;
            } catch (SQLException ex1) {
                System.err.println("Error gordo");
            }
        }
        return false;
    }

    public void cerrarConexion() {
        try {
            //TO-DO
            con.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(OperacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void anadeAlumno(String dni, String nombre, String apellido, String edad) {

        String sentSQL = "INSERT INTO alumnos (dni, nombre, apellido, edad) VALUES (?, ?, ?, ?)";
        PreparedStatement sentencia = null;

        try {

            sentencia = con.prepareStatement(sentSQL);
            sentencia.setString(1, dni);
            sentencia.setString(2, nombre);
            sentencia.setString(3, apellido);
            sentencia.setInt(4, Integer.parseInt(edad));
            sentencia.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void consultaAlumno(String id) {

        String busqueda = "SELECT * FROM alumnos WHERE dni = ?";
        PreparedStatement consulta = null;
        ResultSet resultado = null;

        try {
            consulta = con.prepareStatement(busqueda);
            consulta.setString(1, id);
            resultado = consulta.executeQuery();
            
            while(resultado.next()){
                System.out.print("Apellido: "+resultado.getString(3));
                System.out.print(" Nombre: "+resultado.getString(2));
                System.out.print(" Id: "+resultado.getString(1));
                System.out.println(" Edad: "+resultado.getInt(4)+"\n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void borrarAlumno(String id) {
        String busqueda = "DELETE FROM alumnos WHERE dni = ?";
        PreparedStatement consulta = null;

        try {
            consulta = con.prepareStatement(busqueda);
            consulta.setString(1, id);
            consulta.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            if(consulta != null){
                try {
                    consulta.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void modificaAlumno(String dni, String nombre, String apellido, String edad) {
        String busqueda = "UPDATE alumnos SET nombre = ?, apellido = ?, edad = ? "+
                "WHERE dni = "+dni;
        PreparedStatement consulta = null;

        try {
            consulta = con.prepareStatement(busqueda);
            consulta.setString(1, nombre);
            consulta.setString(2, apellido);
            consulta.setInt(3, Integer.parseInt(edad));
            consulta.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            if(consulta != null){
                try {
                    consulta.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void listadoAlumno() {
        
        String sentSQL = "SELECT * FROM alumnos";
        PreparedStatement sentencia = null;
        ResultSet resultado = null;

        try {
            sentencia = con.prepareStatement(sentSQL);
            resultado = sentencia.executeQuery();
            
            while(resultado.next()){
                System.out.print("Apellido: "+resultado.getString(3));
                System.out.print(" Nombre: "+resultado.getString(2));
                System.out.print(" Id: "+resultado.getString(1));
                System.out.println(" Edad: "+resultado.getInt(4));
            }
            System.out.println("");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                sentencia.close();
                resultado.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
