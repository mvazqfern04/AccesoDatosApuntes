/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pruebas.JavaYBaseDatos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author node
 */
public class Conectores {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.err.println("Excepcionn: " + e.toString());
        }

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/universidad", "root", "abc123."); Statement stmt = con.createStatement();) {

            //stmt.execute("create database universidad");
            String tablaprofesores = "CREATE TABLE IF NOT EXISTS profesores  (dni VARCHAR(9) not NULL, "
                    + " nombre VARCHAR(255), "
                    + " edad INTEGER, "
                    + " PRIMARY KEY ( dni ))";
            stmt.executeUpdate(tablaprofesores);

            String insertarprofesor = " INSERT INTO profesores VALUES ( '1234A', 'JOSE', '55')";
            String insertarprofesor2 = " INSERT INTO profesores VALUES ( 'djkks', 'manuel', '55')";
//            stmt.executeUpdate(insertarprofesor2);

            String borrartabla = "DROP TABLE profesores";

            //stmt.executeUpdate(borrartabla);
            String verprofesor = " select * from profesores";

            ResultSet rs = stmt.executeQuery(verprofesor);

            mostrarProfesores(rs);

            String modificaredad = "UPDATE profesores SET edad = 12 WHERE DNI='1234A'";
            //stmt.executeUpdate(modificaredad);
            ResultSet rs2 = stmt.executeQuery(verprofesor);
            while (rs2.next()) {
                String dni = rs2.getString("dni");
                String nombre = rs2.getString("nombre");
                int edad = rs2.getInt("edad");
                System.out.println(dni + nombre + edad);
            }
            mostrarProfesores(rs2);
//            String borrarprofe = "DELETE FROM profesores WHERE DNI='1234A'";
//            //stmt.executeUpdate(borrarprofe);
//            ResultSet rs3 = stmt.executeQuery(verprofesor);    
//            while (rs3.next()){
//                 String dni = rs3.getString("dni");
//                 String nombre = rs3.getString("nombre"); 
//                 int edad = rs3.getInt("edad");
//                 System.out.println("borrado: " +dni + nombre + edad);
//            }

        } catch (SQLException ex) {
            Logger.getLogger(Conectores.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void mostrarProfesores(ResultSet rs) throws SQLException {
        while (rs.next()) {
            String dni = rs.getString("dni");
            String nombre = rs.getString("nombre");
            int edad = rs.getInt("edad");
            System.out.println("-dni :" + dni + " -nombre :" + nombre + " -edad :" + edad);
        }

    }

}
