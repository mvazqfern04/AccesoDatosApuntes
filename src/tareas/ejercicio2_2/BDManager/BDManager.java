/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareas.ejercicio2_2.BDManager;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import tareas.Ejercicio2_1.OperacionDB;
import tareas.ejercicio2_2.Entidades.*;

/**
 *
 * @author node
 */
public class BDManager {

    private Connection con;
    private Statement stmt;

    /**
     * tractores-> id(int) /modelo /velocidad(int) /precio_venta(float)
     * /id_construccion(int) granjeros-> id(int) /nombre /descripcion
     * /dinero(float) /puntos(int) /nivel(int) construcciones-> id(int) /nombre
     * /precio_compra(float) /precio_venta(float) /proxima_cosecha
     * /id_granjero(int)
     */
    public void abrirConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.err.println("Excepcionn: " + e.toString());
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmville", "root", "abc123.");
            stmt = con.createStatement();

        } catch (SQLException ex) {
            try {
                System.err.println("Error con la clase Connection");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "abc123.");
                stmt = con.createStatement();

            } catch (SQLException ex1) {
                System.err.println("Error gordo");
            }
        }
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

    //Crear->granjero/construccion/tractor
    public void crearGranjero(Granjero gr) {
        try {
            String stringSQL = "INSERT INTO granjeros (id, nombre, descripcion, dinero, puntos, nivel) VALUES (?, ?, ?, ?,?, ?)";
            //String granjSent = "INSERT INTO granjeros (id,nombre, descripcion, dinero,puntos, nivel) VALUES (?, ?, ?, ?,?,?)";
            //String constSent = "INSERT INTO construcciones (id, nombre,precio_compra, precio_venta, proxima_cosecha, id_granjero) VALUES (?, ?, ?, ?,?,?)";
            PreparedStatement sentencia = null;

            sentencia = con.prepareStatement(stringSQL);
            sentencia.setInt(1, gr.getId());
            sentencia.setString(2, gr.getNombre());
            sentencia.setString(3, gr.getDescripcion());
            sentencia.setFloat(4, gr.getDinero());
            sentencia.setInt(5, gr.getPuntos());
            sentencia.setInt(6, gr.getNivel());

            sentencia.executeUpdate();
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void crearConstrucion(Construccion cons) {
        try {
            String stringSQL = "INSERT INTO construcciones (id, nombre, precio, id_granjero) VALUES (?, ?, ?, ?)";
            String stringSQLRestarPrecio = "UPDATE granjeros SET dinero = (dinero-" + cons.getPrecio() + ") WHERE id = " + cons.getId_granjero();
            PreparedStatement sentencia = null;
            PreparedStatement sentenciaRestar = null;

            sentencia = con.prepareStatement(stringSQL);
            sentenciaRestar = con.prepareStatement(stringSQLRestarPrecio);
            sentencia.setInt(1, cons.getId());
            sentencia.setString(2, cons.getNombre());
            sentencia.setFloat(3, cons.getPrecio());
            sentencia.setInt(4, cons.getId_granjero());

            sentencia.executeUpdate();
            sentenciaRestar.executeUpdate();
            sentencia.close();
            sentenciaRestar.close();
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void crearTractor(Tractor tractor) {
        try {
            String stringSQL = "INSERT INTO tractores (id, modelo, velocidad, precio_venta, id_construccion) VALUES (?, ?, ?, ?,?)";
            //String granjSent = "INSERT INTO granjeros (id,nombre, descripcion, dinero,puntos, nivel) VALUES (?, ?, ?, ?,?,?)";
            //String constSent = "INSERT INTO construcciones (id, nombre,precio_compra, precio_venta, proxima_cosecha, id_granjero) VALUES (?, ?, ?, ?,?,?)";
            PreparedStatement sentencia = null;

            sentencia = con.prepareStatement(stringSQL);
            sentencia.setInt(1, tractor.getId());
            sentencia.setString(2, tractor.getTipoTractor());
            sentencia.setInt(3, tractor.getVelocidad());
            sentencia.setFloat(4, tractor.getPrecio_venta());
            sentencia.setInt(5, tractor.getId_construccion());

            sentencia.executeUpdate();
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Modificar->granjero/construccion/tractor
    public void modificarGranjero(Granjero gr) {
        String stringSQL = "UPDATE granjeros SET nombre = ?, descripcion = ?, dinero = ?, puntos = ?, nivel = ? WHERE id = ?";
        PreparedStatement consulta = null;

        try {
            consulta = con.prepareStatement(stringSQL);
            consulta.setString(1, gr.getNombre());
            consulta.setString(2, gr.getDescripcion());
            consulta.setFloat(3, gr.getDinero());
            consulta.setInt(4, gr.getPuntos());
            consulta.setInt(5, gr.getNivel());
            consulta.setInt(6, gr.getId());

            consulta.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (consulta != null) {
                try {
                    consulta.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void modificarConstruccion(Construccion cons) {
        //id, nombre, precio, id_granjero
        String stringSQL = "UPDATE construcciones SET nombre = ?, precio = ?, id_granjero = ? WHERE id = ?";
        PreparedStatement consulta = null;

        try {
            consulta = con.prepareStatement(stringSQL);
            consulta.setString(1, cons.getNombre());
            consulta.setFloat(2, cons.getPrecio());
            consulta.setInt(3, cons.getId_granjero());
            consulta.setInt(4, cons.getId());

            consulta.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (consulta != null) {
                try {
                    consulta.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void modificarTractor(Tractor tr) {
        //id, nombre, precio, id_granjero
        String stringSQL = "UPDATE tractores SET modelo = ?, velocidad = ?, precio_venta = ?, id_construccion = ? WHERE id = ?";
        PreparedStatement consulta = null;

        try {
            consulta = con.prepareStatement(stringSQL);
            consulta.setString(1, tr.getTipoTractor());
            consulta.setInt(2, tr.getVelocidad());
            consulta.setFloat(3, tr.getPrecio_venta());
            consulta.setFloat(4, tr.getId_construccion());
            consulta.setInt(5, tr.getId());

            consulta.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (consulta != null) {
                try {
                    consulta.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    //Eliminar->granjero/construccion/tractor
    public void eliminarGranjero(Granjero gr) {
        String busqueda = "DELETE FROM granjeros WHERE id = ?";
        PreparedStatement consulta = null;

        try {
            consulta = con.prepareStatement(busqueda);
            consulta.setInt(1, gr.getId());
            consulta.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (consulta != null) {
                try {
                    consulta.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void eliminarConstruccion(Construccion cons) {
        String busqueda = "DELETE FROM construcciones WHERE id = ?";
        PreparedStatement consulta = null;

        try {
            consulta = con.prepareStatement(busqueda);
            consulta.setInt(1, cons.getId());
            consulta.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (consulta != null) {
                try {
                    consulta.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void eliminarTractor(Tractor tr) {
        String busqueda = "DELETE FROM tractores WHERE id = ?";
        PreparedStatement consulta = null;

        try {
            consulta = con.prepareStatement(busqueda);
            consulta.setInt(1, tr.getId());
            consulta.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (consulta != null) {
                try {
                    consulta.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    //Leer->granjero/construccion/tractor
    public Granjero verGranjero(Integer id) {
        Granjero salida = new Granjero();
        String busqueda = "SELECT * FROM granjeros WHERE id = ? ";
        try {
            //id(int) /nombre /descripcion /dinero(float) /puntos(int) /nivel(int)
            PreparedStatement consulta = con.prepareStatement(busqueda);
            consulta.setInt(1, id);
            ResultSet rs = consulta.executeQuery();
            rs.next();
            salida.setId(rs.getInt(1));
            salida.setNombre(rs.getString(2));
            salida.setDescripcion(rs.getString(3));
            salida.setDinero(rs.getFloat(4));
            salida.setPuntos(rs.getInt(5));
            salida.setNivel(rs.getInt(6));

        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return salida;
    }

    public Construccion verConstruccion(int id) {
        Construccion salida = new Construccion();
        String busqueda = "SELECT * FROM construcciones WHERE id = " + id;
        try {
            PreparedStatement consulta = con.prepareStatement(busqueda);
            ResultSet rs = consulta.executeQuery();
            rs.next();
            salida.setId(rs.getInt(id));
            salida.setNombre(rs.getString("nombre"));
            salida.setPrecio(rs.getInt("precio"));
            salida.setId_granjero(rs.getInt("id_granjero"));

        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return salida;
    }

    public Tractor verTractor(int id) {
        Tractor salida = new Tractor();
        String busqueda = "SELECT * FROM tractores WHERE id = " + id;
        try {
            PreparedStatement consulta = con.prepareStatement(busqueda);
            ResultSet rs = consulta.executeQuery();
            rs.next();
            salida.setId(rs.getInt("id"));
            salida.setTipoTractor(rs.getString("modelo"));
            salida.setPrecio_venta(rs.getFloat("precio_venta"));
            salida.setId_construccion(rs.getInt("id_construccion"));

        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return salida;
    }

    //Ejercicio parte 2
    public void contruccionesDeGranjero(int idGranjero) {
        String consulta = "SELECT g.nombre AS nomGranj, COUNT(c.id) AS total FROM granjeros g INNER JOIN construcciones c ON g.id = c.id_granjero WHERE g.id = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(consulta);
            ps.setInt(1, idGranjero);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Granjero " + rs.getString("nomGranj") + " tiene " + rs.getInt("total") + " construcciones");
            } else {
                System.err.println("No existe");
            }

        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void aumentarPrecioTractoresRurales() {
        try {
            String updateQuery = "UPDATE tractores SET precio_venta = precio_venta * 1.10 WHERE tipo = 'rural'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(updateQuery);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void listarTractorYSusConstruccines() {
        try {

            String busquedaTr = "SELECT t.modelo, c.nombre FROM tractores t INNER JOIN construcciones c ON c.id = t.id_construccion GROUP BY t.modelo, c.nombre;";

            PreparedStatement consulta = con.prepareStatement(busquedaTr);
            ResultSet rs = consulta.executeQuery();
            while(rs.next()){
                System.out.println("Modelo "+rs.getString("modelo")+" hizo construccion: "+rs.getString("nombre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listarPlantaciones() {
        try {
            String consulta = "SELECT g.nombre AS granjero_nombre, p.nombre AS nombreCosecha FROM granjeros g INNER JOIN plantaciones p ON g.id = p.id_granjero";
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                if((rs.getString("granjero_nombre")==null)||(rs.getString("nombreCosecha")==null)){
                    continue;
                }
                System.out.println("Granjero "+rs.getString("granjero_nombre")+" plantó: "+rs.getString("nombreCosecha"));
            }
        } catch (SQLException ex) {
        }
        
    }

}
