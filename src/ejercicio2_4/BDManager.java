package ejercicio2_4;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdempresa", "root", "abc123.");
            stmt = con.createStatement();
            System.out.println("ENtra");

        } catch (SQLException ex) {
            System.out.println("No entra");
            try {
                System.err.println("Error con la clase Connection");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "abc123.");
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

    //Aquí empieza lo del ejerccio 2.4[
    //Subir salrio empregados según departamento
    public void aumentarSalario(String cantidad, String nomDepa) {
        try {
            PreparedStatement amano = con.prepareStatement("SET SQL_SAFE_UPDATES=0");
            amano.execute();

            String update = "update empregado inner join departamento on empregado.num_departamento_pertenece = departamento.num_departamento set empregado.salario = empregado.salario + " + cantidad + "  where departamento.nome_departamento = '" + nomDepa + "'";
            PreparedStatement sentencia = con.prepareStatement(update);
            sentencia.executeUpdate();

            amano = con.prepareStatement("SET SQL_SAFE_UPDATES=1");
            amano.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Insera depa nuevo
    public void inserDepa(String num, String nomDepa, String nssDirector) {
        try {
            DateFormat datFor = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calen = Calendar.getInstance();
            String actu = datFor.format(calen.getTime());

            String inser = "insert into departamento (num_departamento, nome_departamento, nss_dirige, data_direccion) values (?, ?, ?,'" + actu + "')";
            PreparedStatement ps = con.prepareStatement(inser);

            ps.setString(1, num);
            ps.setString(2, nomDepa);
            ps.setString(3, nssDirector);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void quitarEmpregadoProxecto(String nss, String cod) {
        try {
            String borrar = "delete from empregado_proxecto where nss_empregado = " + nss + " and num_proxecto = " + cod;
            PreparedStatement ps = con.prepareStatement(borrar);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostarLocali(String local) {
        try {
            String ver = "select e.nombre, e.apelido_1, e.apelida_2, e.localidade, e.salario, e.data_nacemento, e.nss as nss_empregado, e.nss_supervisa as nss_super_empregado, "
                    + "j.nombre as nom_jefe, d.nome_departamento "
                    + "from empregado e "
                    + "inner join empregado j on e.nss_supervisa = j.nss "
                    + "inner join departamento d on e.num_departamento_pertenece = d.num_departamento "
                    + "where e.localidade = '" + local + "'";
            PreparedStatement ps = con.prepareStatement(ver);
            //(nome xefe), (nome departamento)
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                System.out.println("Nombre: " + rs.getString("nombre")
                        + " /Apelidos: " + rs.getString("apelido_1") + rs.getString("apelida_2")
                        + " /Localidade: " + rs.getString("localidade")
                        + " /Salario: " + rs.getString("salario")
                        + " /Nacemento: " + rs.getString("data_nacemento")
                        + " /Nombre jefe:" + rs.getString("nom_jefe")
                        + " /Nombre departamento: " + rs.getString("nome_departamento"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Ejercicio 3
    public void changeDepaFromProxecto(String nomDepa, String nomProxe) {
        try {
            String update = "update proxecto p "
                    + "inner join departamento d "
                    + "on d.nome_departamento = '" + nomDepa + "'"
                    + "set p.num_departamento = d.num_departamento "
                    + "where p.nome_proxecto = '" + nomProxe + "'";
            PreparedStatement ps = con.prepareStatement(update);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void borrarProxecto(int numProxe) {
        try {
            String borrar = "delete from proxecto where num_proxecto=" + numProxe;
            String borrar2 = "delete from empregado_proxecto where num_proxecto=" + numProxe;
            PreparedStatement ps = con.prepareStatement(borrar);
            ps.executeUpdate();
            ps = con.prepareStatement(borrar2);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Ejercicio 4
    public ArrayList<Proxecto> obtenerProxecto(String nomDepa) {
        ArrayList<Proxecto> salida = new ArrayList();
        try {

            String consul = "select * from proxecto p "
                    + "inner join departamento d on p.num_departamento = d.num_departamento "
                    + "where d.nome_departamento = '" + nomDepa + "'";
            PreparedStatement ps = con.prepareStatement(consul);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Integer numPro = rs.getInt("num_proxecto");
                String nome_proxecto = rs.getString("nome_proxecto");
                String lugar = rs.getString("lugar");
                Integer numDepa = rs.getInt("num_departamento");
                salida.add(new Proxecto(numPro, numDepa, lugar, nome_proxecto));
            }

        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salida;
    }

    //Ejercicio 5
    public void sp_cambioDomicilio(String nss, String rua, Integer numeroRua, String piso, int cp, String locali) {
        try {
            CallableStatement cstmt = con.prepareCall("{call pr_cambioDomicilio(?, ?, ?, ?, ?, ?)}");
            cstmt.setString(1, nss);
            cstmt.setString(2, rua);
            cstmt.setInt(3, numeroRua);
            cstmt.setString(4, piso);
            cstmt.setInt(5, cp);
            cstmt.setString(6, locali);
            cstmt.execute();
        } catch (SQLException ex) {
            System.err.println("Error");
        }
    }

    public void proceDatosProxecto(int numProx) {
        try {
            CallableStatement cs = con.prepareCall("{call pr_DatosProxecto(?,?,?,?)}");
            cs.setInt(1, numProx);
            cs.execute();

            cs.registerOutParameter(2, Types.VARCHAR); // nombre del proyecto
            cs.registerOutParameter(3, Types.VARCHAR); // lugar del proyecto
            cs.registerOutParameter(4, Types.INTEGER);

            String nome = cs.getString(2);
            String lugar = cs.getString(3);
            Integer numDepa = cs.getInt(4);

            Proxecto pro = new Proxecto(numProx, numDepa, nome, lugar);
            System.out.println(pro.toString());
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void comprobarCantidadProyectosDeDepas(int cant) {
        try {
            CallableStatement cs = con.prepareCall("{call pr_DepartControlaProxec2(?)}");
            cs.setInt(1, cant);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                String num = rs.getString("num_departamento");
                String nome = rs.getString("nome_departamento");
                String nss = rs.getString("nss_dirige");
                String data = rs.getString("data_direccion");
                System.out.println("Num: " + num);
                System.out.println("Nome: " + nome);
                System.out.println("Nss: " + nss);
                System.out.println("Data: " + data);
                System.out.println("--------------------------");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void contarEmpregadosPorDepa(String nomDepa) {
        String consul = "SELECT fn_nEmpDepart('" + nomDepa + "') as total";
        try {
            PreparedStatement ps = con.prepareStatement(consul);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String totalEmpregados = rs.getString(1);
            System.out.println("En " + nomDepa + " existen " + totalEmpregados);
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Ejercicio numero 6
    public void tipoResultSet() {
        //Lo he mirado
    }

    public boolean insetao(Proxecto pro) {
        try {
            Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = s.executeQuery("select * from proxecto");

            //TODO comprobar si existe proxecto por nombre y otro por numero
            if (!comprobarExisNumDepa(pro)) {
                return false;
            }
            if (comprobarSiNomNumPro(pro, rs)) {
                return false;
            }

            rs.moveToInsertRow();
            rs.updateInt("num_proxecto", pro.getNum_proxecto());
            rs.updateString("nome_proxecto", pro.getNome_proxecto());
            rs.updateString("lugar", pro.getLugar());
            rs.updateInt("num_departamento", pro.getNum_departamento());
            rs.insertRow();

        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    //true si existe
    private boolean comprobarSiNomNumPro(Proxecto proxe, ResultSet rs) {
        try {
            String nom = proxe.getNome_proxecto();
            Integer num = proxe.getNum_proxecto();

            rs.beforeFirst();

            while (rs.next()) {
                if (nom.equals(rs.getString("nome_proxecto"))) {
                    return true;
                }
                if (num.equals(rs.getInt("num_proxecto"))) {
                    return true;
                }
            }

            rs.beforeFirst();

        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private boolean comprobarExisNumDepa(Proxecto pro) {
        try (PreparedStatement s = con.prepareStatement("select num_departamento from departamento")) {
            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                if (pro.getNum_departamento().equals(rs.getInt("num_departamento"))) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public void incrementarSueldoDepa(Integer incre, Integer nuDep) {
        try {
            Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            try (ResultSet rs = s.executeQuery("select nss, salario, num_departamento_pertenece from empregado")) {
                while (rs.next()) {
                    if (nuDep.equals(rs.getInt("num_departamento_pertenece"))) {
                        int salario = rs.getInt("salario");
                        rs.updateInt("salario", salario + incre);
                        rs.updateRow();
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void verDeFormaEnrevesada(int cantProx) {
        try {
            Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery("select nombre, localidade, apelido_1, apelida_2, nss, salario, count(p.num_proxecto) as cantPro from empregado e "
                    + "inner join empregado_proxecto ep on nss = nss_empregado "
                    + "inner join proxecto p on p.num_proxecto =  ep.num_proxecto "
                    + "group by e.nss "
                    + "having count(p.num_proxecto) >= " + cantProx);
            
            System.out.println("Primero:");
            rs.beforeFirst();
            rs.next();
            leerRsEmpre(rs, cantProx);
            
            System.out.println("Ultimo:");
            rs.afterLast();
            rs.previous();
            leerRsEmpre(rs, cantProx);
            
            System.out.println("Penultimo:");
            rs.previous();
            leerRsEmpre(rs, cantProx);
            
            System.out.println("Todo a la inversa:");
            rs.afterLast();
            while(rs.previous()){
                leerRsEmpre(rs, cantProx);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void leerRsEmpre(ResultSet rs, int cantProx) {
        try {
            if (cantProx <= rs.getInt("cantPro")) {
                String loca = rs.getString("localidade");
                String nome = rs.getString("nombre") + " ," + rs.getString("apelido_1") + ", " + rs.getString("apelida_2");
                String nss = rs.getString("nss");
                String salario = rs.getString("salario");
                System.out.println("Nss: " + nss);
                System.out.println("Nome: " + nome);
                System.out.println("Localidade: " + loca);
                System.out.println("Salario: " + salario);
                System.out.println("--------------------------");
            }
        } catch (SQLException ex) {
            System.out.println("No existe.");
        }
    }

}
