/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class CreateDB {

    public CreateDB() {
        drop();
        createTypes();
        createTable();
        insertData();
        createFunction();
    }
    
    public void drop() {
        String query = "DROP TABLE IF EXISTS cliente;" +
                        "DROP TABLE IF EXISTS paquete_vacacional;" +
                        "DROP TABLE IF EXISTS alojamiento;" +
                        "DROP DOMAIN IF EXISTS telefono_type;" +
                        "DROP DOMAIN IF EXISTS dni_type;" +
                        "DROP FUNCTION IF EXISTS consulta_cliente(varchar);" +
                        "DROP TYPE IF EXISTS vuelo_type;" +
                        "DROP TYPE IF EXISTS direccion_type;" +
                        "DROP TYPE IF EXISTS alojamiento_type;";
        
        try{
            ConnectionDB connection = new ConnectionDB();
            Statement pstm = connection.getConnection().createStatement();
            
            pstm.executeQuery(query).close();
            
            JOptionPane.showMessageDialog(null, "Los Datos se han borrado correctamente");
            connection.closeDB();
        }catch(SQLException ex){
            if(ex.getErrorCode() != 0)
                JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
    }
    
    public void createTypes() {
        String query = "CREATE DOMAIN telefono_type as varchar(9) CHECK(VALUE ~ '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]');" +
                        "CREATE DOMAIN dni_type as varchar(9) CHECK(VALUE ~ '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][a-zA-Z]');" +
                        "CREATE TYPE vuelo_type AS (" +
                        "	aeropuerto_origen varchar(60)," +
                        "	aeropuerto_destino varchar(60)," +
                        "	aeropuertos_escala varchar(60)[]," +
                        "	compa??ia varchar(50)," +
                        "	numero_vuelos integer" +
                        ");" +
                        "CREATE TYPE direccion_type AS (" +
                        "	numero integer," +
                        "	calle varchar(50)," +
                        "	pais varchar(50)," +
                        "	provincia varchar(50)" +
                        ");" +
                        "CREATE TYPE alojamiento_type AS ENUM ('Hotel', 'Hostal', 'Apartamento');" ;
        
        try{
            ConnectionDB connection = new ConnectionDB();
            Statement pstm = connection.getConnection().createStatement();
            
            pstm.executeQuery(query).close();
            
            JOptionPane.showMessageDialog(null, "Los tipos estrcturados se han creado correctamente");
            connection.closeDB();
        }catch(SQLException ex){
            if(ex.getErrorCode() != 0)
                JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
    }
    
    public void createFunction() {
        String query = "CREATE FUNCTION consulta_cliente(variable varchar) returns varchar as $$" +
                        "	select " +
                        "	c.nombre || ' ' || c.apellidos || ' viaj?? el ' || vp.fecha_inicio || ' y se aloj?? en el hotel ' || a.establecimiento " +
                        "	from cliente as c " +
                        "	join paquete_vacacional as vp" +
                        "	on c.paquete_vacacional = vp.id" +
                        "	join alojamiento as a" +
                        "	on vp.alojamiento = a.id" +
                        "	where c.dni = dni; " +
                        "	$$ LANGUAGE SQL;";
        
        try{
            ConnectionDB connection = new ConnectionDB();
            Statement pstm = connection.getConnection().createStatement();
            
            pstm.executeQuery(query).close();
            
            JOptionPane.showMessageDialog(null, "La funcion se ha creado correctamente");
            connection.closeDB();
        }catch(SQLException ex){
            if(ex.getErrorCode() != 0)
                JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
    }
    
    public void createTable() {
        String query = "CREATE TABLE alojamiento(" +
                        "	id SERIAL PRIMARY KEY," +
                        "	establecimiento varchar(60)," +
                        "	direccion direccion_type," +
                        "	tipo_establecimiento alojamiento_type," +
                        "	categoria integer CHECK (1 <= categoria AND categoria <= 5)" +
                        ");" +
                        "CREATE TABLE paquete_vacacional (" +
                        "	id SERIAL PRIMARY KEY," +
                        "	fecha_inicio date," +
                        "	fecha_fin date," +
                        "	alojamiento integer," +
                        "	vuelo_ida vuelo_type," +
                        "	vuelo_fin vuelo_type," +
                        "	FOREIGN KEY (alojamiento) REFERENCES alojamiento(id)" +
                        ");" +
                        "CREATE TABLE cliente (" +
                        "	dni dni_type PRIMARY KEY," +
                        "	nombre varchar(25)," +
                        "	apellidos varchar(50)," +
                        "	telefono telefono_type[]," +
                        "	direccion direccion_type," +
                        "	paquete_vacacional integer," +
                        "	FOREIGN KEY (paquete_vacacional) REFERENCES paquete_vacacional(id)" +
                        ");";
        
        try{
            ConnectionDB connection = new ConnectionDB();
            Statement pstm = connection.getConnection().createStatement();
            
            pstm.executeQuery(query).close();
            
            JOptionPane.showMessageDialog(null, "Las Tablas se han creado correctamente");
            connection.closeDB();
        }catch(SQLException ex){
            if(ex.getErrorCode() != 0)
                JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
    }
        
    public void insertData() {
        String query = "INSERT INTO alojamiento (establecimiento, direccion, tipo_establecimiento, categoria) VALUES" +
                        "	('Establecimiento 1', (4, 'Calle jkgh', 'Espa??a', 'S/C de Tenerife'), 'Hostal', 2)," +
                        "	('Establecimiento 2', (5, 'Calle jkjg', 'Espa??a', 'S/C de Tenerife'), 'Hostal', 2)," +
                        "	('Establecimiento 3', (78, 'Calle jkhgjk', 'Espa??a', 'S/C de Tenerife'), 'Hotel', 2)," +
                        "	('Establecimiento 4', (45, 'Calle hjkhgj', 'Espa??a', 'S/C de Tenerife'), 'Apartamento', 2);" +
                        "INSERT INTO paquete_vacacional (fecha_inicio, fecha_fin, alojamiento, vuelo_ida, vuelo_fin) VALUES" +
                        "	('2022-04-02', '2022-06-03', 1, ('Paris', 'Brucelas', ARRAY['Berl??n'], 'Compa??ia 1', 2), ('Brucelas', 'Paris', NULL, 'Compa??ia 2', 1))," +
                        "	('2022-05-10', '2022-05-20', 1, ('Madrid', 'Tenerife', NULL, 'Compa??ia 3', 1), ('Tenerife', 'Madrid', NULL, 'Compa??ia 3', 1))," +
                        "	('2022-07-01', NULL, 1, ('Londres', 'Mil??n', ARRAY['Paris'], 'Compa??ia 2', 3), NULL);" +
                        "INSERT INTO cliente (dni, nombre, apellidos, telefono, direccion, paquete_vacacional) VALUES " +
                        "	('11111111A', 'Ana', 'Perez', ARRAY['123456789'], (6, 'Calle hjkhgjjjj', 'Espa??a', 'S/C de Tenerife'), 1)," +
                        "	('22222222B', 'Antonio', 'Armas', ARRAY['456123789'], (7, 'Calle gjdl', 'Espa??a', 'Madrid'), 2)," +
                        "	('33333333C', 'Mario', 'Hern??ndez', ARRAY['789456123'], (545, 'Calle fjgdfgkskf', 'Espa??a', 'Huelva'), 3)," +
                        "	('44444444D', 'Mar??a', 'Perez', ARRAY['123456789'], (6, 'Calle hjkhgjjjj', 'Espa??a', 'S/C de Tenerife'), 1)," +
                        "	('55555555E', 'Jose', 'Armas', ARRAY['456123789'], (7, 'Calle gjdl', 'Espa??a', 'Madrid'), 2)," +
                        "	('66666666F', 'Marta', 'Hern??ndez', ARRAY['789456123', '123456123'], (545, 'Calle fjgdfgkskf', 'Espa??a', 'Huelva'), 3);  ";
        
        try{
            ConnectionDB connection = new ConnectionDB();
            Statement pstm = connection.getConnection().createStatement();
            
            pstm.executeQuery(query).close();
            
            JOptionPane.showMessageDialog(null, "Los Datos se han insertado correctamente");
            connection.closeDB();
        }catch(SQLException ex){
            if(ex.getErrorCode() != 0)
                JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
    }
}
