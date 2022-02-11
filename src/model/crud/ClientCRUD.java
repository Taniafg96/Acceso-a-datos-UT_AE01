package model.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.ConnectionDB;
import model.pojo.ClientPojo;
import model.pojo.HousingPojo;
import model.pojo.VacationPackagePojo;

/**
 *
 * @author Tania Fariña González
 */

public class ClientCRUD{
    private static final int CLIENTCOLUMNS = 6; 
    
    public void create(ClientPojo client) {
        String query = "INSERT INTO cliente (dni, nombre, apellidos, telefono, direccion, paquete_vacacional) VALUES \n" +
"	(?, ?, ?, ?, (?, ?, ?, ?), ?)";
        
        try{
            ConnectionDB connection = new ConnectionDB();
            PreparedStatement pstm = connection.getConnection().prepareStatement(query);
            
            
            System.out.println(client.getPhone());
            
            pstm.setString(1, client.getDni());
            pstm.setString(2, client.getName());
            pstm.setString(3, client.getLastName());
            pstm.setString(4, client.getPhone());
            pstm.setInt(5, client.getDirection().getNumber());
            pstm.setString(6, client.getDirection().getStreet());
            pstm.setString(7, client.getDirection().getCountry());
            pstm.setString(8, client.getDirection().getProvince());
            pstm.setInt(9, client.getVacationPackage());
            
            pstm.executeQuery().close();
            
            JOptionPane.showMessageDialog(null, "El cliente se ha insertado "
                    + "correctamente");
            
            connection.closeDB();
        }catch(SQLException ex){
            if(ex.getErrorCode() != 0)
                JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
    }
    
    public List<Object[]> read() {
        String query = "select dni, nombre, apellidos, telefono[1], "
                + "'C/' || (direccion).calle || ' número ' || (direccion).numero || ' ' || (direccion).pais || ' ' || (direccion).provincia"
                + ", paquete_vacacional from cliente";
        
        List <Object[]> vacationalPackageList = new ArrayList<>();
        
        try{
            ConnectionDB connection = new ConnectionDB();
            Statement stm = connection.getConnection().createStatement();
            
            ResultSet rs = stm.executeQuery(query);
            
            while(rs.next()){
                Object[] vacationalPackageObject = new Object[CLIENTCOLUMNS];
                
                for(int i = 0; i < CLIENTCOLUMNS; i++){
                    vacationalPackageObject[i] = rs.getObject(i+1);
                }
                
                vacationalPackageList.add(vacationalPackageObject);
            }
            
            connection.closeDB();
            stm.close();
            return vacationalPackageList;
        }catch(SQLException ex){
            if(ex.getErrorCode() != 0)
                JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
            
            return null;
        }
    }

    public void update(String query) {
        try{
            ConnectionDB connection = new ConnectionDB();
            Statement stm = connection.getConnection().createStatement();
            
            stm.executeQuery(query).close();
            
            JOptionPane.showMessageDialog(null, "El cliente se ha actualizado "
                    + "correctamente");
            
            connection.closeDB();
        }catch(SQLException ex){
            if(ex.getErrorCode() != 0)
                JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
    }

    public void delete(String id) {
        String query = "DELETE FROM cliente WHERE dni = ?";
        
        try{
            ConnectionDB connection = new ConnectionDB();
            PreparedStatement pstm = connection.getConnection().prepareStatement(query);
            
            pstm.setString(1, id);
         
            pstm.executeQuery().close();
            
            JOptionPane.showMessageDialog(null, "El cliente se ha borrado "
                    + "correctamente");
            connection.closeDB();
        }catch(SQLException ex){
            if(ex.getErrorCode() != 0)
                JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
    }
    
    public Object[] search(String dni){
        String query = "select dni, nombre, apellidos, telefono[1], "
                + "'C/' || (direccion).calle || ' número ' || (direccion).numero || ' ' || (direccion).pais || ' ' || (direccion).provincia"
                + ", paquete_vacacional from cliente where dni = ?";
        
        try{
            ConnectionDB connection = new ConnectionDB();
            PreparedStatement pstm = connection.getConnection().prepareStatement(query);

            pstm.setString(1, dni);
            
            ResultSet rs = pstm.executeQuery();
            
            Object[] vacationalPackageObject = new Object[CLIENTCOLUMNS];
            
            while(rs.next()){
                for(int i = 0; i < CLIENTCOLUMNS; i++){
                    vacationalPackageObject[i] = rs.getObject(i+1);
                }
            }
            
            pstm.close();
            connection.closeDB();            
            
            return vacationalPackageObject;
        }catch(SQLException ex){
            if(ex.getErrorCode() != 0)
                JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
            
            return null;
        }
    }
    
    public String consult(String dni){
        String query = "select consulta_cliente(?)";
        String result = "";
        try{
            ConnectionDB connection = new ConnectionDB();
            PreparedStatement pstm = connection.getConnection().prepareStatement(query);

            pstm.setString(1, dni);
            
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                result = rs.getString(1);
            }

            pstm.close();
            connection.closeDB();              
            return result;
        }catch(SQLException ex){
            if(ex.getErrorCode() != 0)
                JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
            
            return null;
        }
    }
    
}
