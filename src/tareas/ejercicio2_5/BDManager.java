/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tareas.ejercicio2_5;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author node
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

    void mostarCosasSXBD() {
        try {
            DatabaseMetaData meta = con.getMetaData();
            System.out.println("Nombre: "+meta.getDatabaseProductName());
            System.out.println("Version: "+meta.getDatabaseProductVersion());
            System.out.println("Version pricipal: "+meta.getDatabaseMajorVersion());
            System.out.println("Version secundaria: "+meta.getDatabaseMinorVersion());
            System.out.println("Conector JDBC: "+meta.getDriverName());
            System.out.println("Conector version JDBC: "+meta.getDriverVersion());
            System.out.println("Conector version principal JDBC: "+meta.getDriverMajorVersion());
            System.out.println("Conector version secundaria JDBC: "+meta.getDriverMinorVersion());
            System.out.println("URL database: "+meta.getURL());
            System.out.println("Usuario principal: "+meta.getUserName());
            System.out.println("Solo lectura?: "+meta.isReadOnly());
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void mostarInfoAllTablas() {
        try {
            DatabaseMetaData meta = con.getMetaData();
            String patron = "%";
            String[] tipos = new String[2];
            tipos[0]="TABLE";
            tipos[1]="TABLE";
            ResultSet rs = meta.getTables(con.getCatalog(),null, patron, tipos);
            while(rs.next()){
                System.out.println("nombre: "+ rs.getString("TABLE_NAME") );
                System.out.println("tipo: "+rs.getString("TABLE_TYPE"));
                System.out.println("esquema: "+rs.getString("TABLE_SCHEM"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void mostraColumnas(String esquema, String tabla) {
        try {
            DatabaseMetaData meta = con.getMetaData();
            
            ResultSet rs = meta.getColumns(con.getCatalog(),esquema, tabla,null);
            while(rs.next()){
                System.out.println("Nombre: "+rs.getString("COLUMN_NAME"));
                System.out.println("Tipo: "+rs.getString("TYPE_NAME"));
                System.out.println("Tamano: "+rs.getString("COLUMN_SIZE"));
                System.out.println("Nulo?: "+Boolean.parseBoolean(rs.getString("NULLABLE")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void verProcedimentos() {
        try {
            DatabaseMetaData meta = con.getMetaData();
            ResultSet rs = meta.getProcedures(con.getCatalog(), null, null);
            while(rs.next()){
                System.out.println("Nombre: "+rs.getString("PROCEDURE_NAME"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void mostrarPrimaryKey(String nom, String esquem) {
        try {
            DatabaseMetaData meta = con.getMetaData();
            ResultSet rs = meta.getPrimaryKeys(con.getCatalog(), esquem, nom);
            while(rs.next()){
                System.out.println("Esquema: "+rs.getString("TABLE_SCHEM"));
                System.out.println("Nombre columna: "+rs.getString("COLUMN_NAME"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void clavesForaneas(String nom, String esquem) {
        try {
            DatabaseMetaData meta = con.getMetaData();
            ResultSet rs = meta.getImportedKeys(con.getCatalog(), esquem, nom);
            while(rs.next()){
                System.out.println("Foreign key: "+rs.getString("FKCOLUMN_NAME"));
                System.out.println("Tabla: "+rs.getString("PKTABLE_NAME"));
                System.out.println("Nombre clave: "+rs.getString("PKCOLUMN_NAME"));
                System.out.println("--------------------");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void ejercicio3() {
        try {
            DatabaseMetaData meta = con.getMetaData();
            System.out.println("Funcions en cadena data e hora: "+meta.getTimeDateFunctions());
            System.out.println("Funcions en cadena matematicas: "+meta.getNumericFunctions());
            System.out.println("Funcions en cadena palabras reservadas: "+meta.getSQLKeywords());
            System.out.println("Funcions en cadena palabras de escape comodín: "+meta.getSearchStringEscape());
            System.out.println("El usuario puede llamar a todas procedures: "+meta.allProceduresAreCallable());
            System.out.println("El usuario puede llamar a todas funciones: "+meta.allTablesAreSelectable());
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void limitesDelConectos() {
        try {
            DatabaseMetaData meta = con.getMetaData();
            System.out.println("Maximo conexiones simultanes: "+meta.getMaxConnections());
            System.out.println("Maximo sentencias simultanes: "+meta.getMaxStatements());
            System.out.println("Maximo tablas por consulta: "+meta.getMaxTablesInSelect());
            System.out.println("Maximo longitud nombre tabla: "+meta.getMaxTableNameLength());
            System.out.println("Maximo longitud nombre columna: "+meta.getMaxColumnNameLength());
            System.out.println("Maximo longitud nombre sentencai: "+meta.getMaxStatementLength());
            System.out.println("Maximo longitud nombre procedimiento: "+meta.getMaxProcedureNameLength());
            System.out.println("Maximo longitud nombre clausura order: "+meta.getMaxColumnsInOrderBy());
            System.out.println("Maximo longitud nombre clausura select: "+meta.getMaxColumnsInSelect());
            System.out.println("Maximo longitud nombre clausura group by: "+meta.getMaxColumnsInGroupBy());
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void infoTransAcciones() {
        try {
            DatabaseMetaData meta = con.getMetaData();
            System.out.println("Soporta transacciones?: "+meta.supportsTransactions());
            System.out.println("Aislamiento de tranacciones predeterminadas?: "+meta.getDefaultTransactionIsolation());
            System.out.println("Soportan sentencia de manipulacion de datos e definicion de datos de transacciones?: "
                    +meta. supportsDataDefinitionAndDataManipulationTransactions());
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void soporteCaracteristicas() {
        try {
            DatabaseMetaData meta = con.getMetaData();
            System.out.println("ALTER TABLE se pode utilizar ADD COLUMN e DROP COLUMN: "+(meta.supportsAlterTableWithAddColumn()&&meta.supportsAlterTableWithDropColumn()));
            System.out.println("alias de columnas pode empregarse a palabra AS: "+meta.supportsColumnAliasing());
            System.out.println("si o resultado de concatenar un NULL e un NOT NULL resulta NULL: "+meta.nullPlusNonNullIsNull());
            System.out.println("si se soportan as conversións entre tipos de datos JDBC: "+meta.supportsConvert());
            System.out.println("si se soportan os nomes de táboas correlacionada :"+meta.supportsTableCorrelationNames());
            System.out.println("si se soporta o uso dunha columna que non está na instrución SELECT nunha cláusula ORDER BY: "+meta.supportsOrderByUnrelated());
            System.out.println("si se soporta a cláusula GROUP BY: "+ meta.supportsGroupBy());
            System.out.println("si se admite o uso dunha columna que non está na instrución SELECT nunha cláusula GROUP BY: "+meta.supportsGroupByUnrelated());
            System.out.println("si se soportan as cláusulas LIKE: "+meta.supportsLikeEscapeClause());
            System.out.println("si se soportan os outer joins,: "+meta.supportsOuterJoins());
            System.out.println("si se soportan subconsultas EXISTS e si se soportan " +
"subconsultas nas expresións de comparación, nas expresións IN e nas nas expresións cuantificadas: "+meta.supportsSubqueriesInComparisons());
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void parametrosDeConsulta(String consulta) {
        try {
            stmt = con.createStatement();
            ResultSet consultaRes = stmt.executeQuery(consulta);
            ResultSetMetaData meta = consultaRes.getMetaData();

            System.out.println("Numero columnas: "+meta.getColumnCount());
            
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.println("Nombre columna: "+meta.getColumnName(i));
                System.out.println("Tipo: "+meta.getColumnTypeName(i));
                System.out.println("Tamano: "+meta.getColumnDisplaySize(i));
                System.out.println("Puede ser nulo?: "+Boolean.valueOf(String.valueOf(meta.isNullable(i))));
                System.out.println("-----------------------------------");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BDManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
