package model;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tania Fariña González
 */

public class ConnectionDB {
        private java.sql.Connection connection;
    private static final String USER = "postgres";
    private static final String PASSWORD = "MTprS9tHEbFkUU";
    private static final String PORT = "3000";
    private static final String DATABASENAME = "UT4-AE1";
    private static final String HOST = "localhost";
    private static final String URL = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASENAME;

    public ConnectionDB() {
        connect();
    }


    private void connect(){
        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Se ha conectado con la base de datos");
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR con el driver" + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("ERROR al conectarse a la base de datos " + ex.getMessage() + ex.getSQLState());
        }
            
    }

    public java.sql.Connection getConnection() {
        return connection;
    }
    
    public void createDB(){
        try {
            new CreateDB();
            System.out.println("La base de datos se ha creado correctamente");
        } catch (Exception ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void closeDB(){
        try {
            connection.close();
            System.out.println("Se ha desconectado la base de datos");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
