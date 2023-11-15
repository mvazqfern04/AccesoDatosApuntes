/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio2_3;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mario
 */
public class BDManager {

    private Connection con;
    private Statement stmt;

    public void abrirConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Funciona");
        } catch (Exception e) {
            System.out.println("No funciona");
            System.err.println("Excepcionn: " + e.toString());
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdempresa", "root", "mario");
            stmt = con.createStatement();
            System.out.println("ENtra");

        } catch (SQLException ex) {
            System.out.println("No entra");
            try {
                System.err.println("Error con la clase Connection");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "mario");
                System.out.println("De aqui no pasa");
                stmt = con.createStatement();

            } catch (SQLException ex1) {
                System.err.println("Error gordo");
            }
        }
    }

    public void cerrarConexion() {
        try {
            con.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void actuEmpregado(Integer nss, Empregado emp) {
        try {
            String sentencia = "update empregado set nombre = ?,apelido_1 = ?,apelida_2 = ? where nss = " + nss;
            PreparedStatement consulta = con.prepareStatement(sentencia);
            consulta.setString(1, emp.getNombre());
            consulta.setString(2, emp.getApelido_1());
            consulta.setString(3, emp.getApelido_2());
            consulta.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertarProxecto(Proxecto prx) {
        try {
            String sentencia = "INSERT INTO PROXECTO VALUES (?,?,?,?);";
            PreparedStatement ejecu = con.prepareStatement(sentencia);
            ejecu.setInt(1, prx.getNum_proxecto());
            ejecu.setString(2, prx.getNome_proxecto());
            ejecu.setString(3, prx.getLugar());
            ejecu.setInt(4, prx.getNum_departamento());
            ejecu.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void instEmpregado(Empregado emp) {
        try {
            String sentencia = "INSERT INTO empregado VALUES (?,?,?,?);";
            PreparedStatement ejecu = con.prepareStatement(sentencia);
            ejecu.setString(1, emp.getNombre());
            ejecu.setString(2, emp.getApelido_1());
            ejecu.setString(3, emp.getApelido_2());
            ejecu.setInt(4, Integer.parseInt(emp.getNss()));

            ejecu.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void busqEmpregado(String nss) {
        try {
            String busc = "select * from empregado where nss = " + nss;
            PreparedStatement ps = con.prepareStatement(busc);
            ResultSet rs = ps.executeQuery();
            Empregado emp = null;
            while (rs.next()) {
                String nom = rs.getString("nombre");
                String apeli1 = rs.getString("apelido_1");
                String apeli2 = rs.getString("apelida_2");
                emp = new Empregado(nom, apeli1, apeli2, nss);
            }
            System.out.println(emp.toString());
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consulDepa(int numDepa) {
        try {
            String busc = "select * from departamento where num_departamento = " + numDepa;
            PreparedStatement ps = con.prepareStatement(busc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("numdeapa" + rs.getInt(1));
                System.out.println("numdeapa" + rs.getString(2));
                System.out.println("numdeapa" + rs.getString(3));
                System.out.println("numdeapa" + rs.getDate(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consulPro(int numPro) {
        try {
            String busc = "select * from proxecto where num_proxecto = " + numPro;
            PreparedStatement ps = con.prepareStatement(busc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("numPro" + rs.getInt(1));
                System.out.println("nomPro" + rs.getString(2));
                System.out.println("lugar" + rs.getString(3));
                System.out.println("numDepa" + rs.getInt(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
