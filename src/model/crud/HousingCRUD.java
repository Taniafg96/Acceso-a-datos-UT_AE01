package model.crud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.ConnectionDB;
import model.pojo.HousingPojo;

/**
 *
 * @author Tania Fariña González
 */

public class HousingCRUD {
    private static final int HOUSINGCOLUMNS = 5; 
    
    public void create(HousingPojo housing) {
        String query = "INSERT INTO alojamiento (establecimiento, direccion, tipo_establecimiento, categoria) VALUES (?, (?, ?, ?, ?), ?, ?)";
        try{
            ConnectionDB connection = new ConnectionDB();
            PreparedStatement pstm = connection.getConnection().prepareStatement(query);

            
            pstm.setString(1, housing.getHouse());
            pstm.setInt(2, housing.getDirection().getNumber());
            pstm.setString(3, housing.getDirection().getStreet());
            pstm.setString(4, housing.getDirection().getCountry());
            pstm.setString(5, housing.getDirection().getProvince());
            pstm.setString(6, housing.getHousingType());
            pstm.setInt(7, housing.getCategory());

            pstm.executeQuery().close();
            
            JOptionPane.showMessageDialog(null, "El alojamiento se ha insertado "
                    + "correctamente");
            
            connection.closeDB();     
        }catch(SQLException ex){
            if(ex.getErrorCode() != 0)
                JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
    }
    
    public List<Object[]> read() {
        String query = "select id, establecimiento, 'C/' || (direccion).calle || ' número ' || (direccion).numero || ' ' || (direccion).pais || ' ' || (direccion).provincia"
                + ", tipo_establecimiento, categoria from alojamiento";
        List <Object[]> housingList = new ArrayList<>();
        
        try{
            ConnectionDB connection = new ConnectionDB();
            Statement stm = connection.getConnection().createStatement();
            
            ResultSet rs = stm.executeQuery(query);
            
            while(rs.next()){
                Object[] housingObject = new Object[HOUSINGCOLUMNS];
                
                for(int i = 0; i < HOUSINGCOLUMNS; i++){
                    housingObject[i] = rs.getObject(i+1);
                }
                
                housingList.add(housingObject);
            }
            
            connection.closeDB();
            stm.close();
            return housingList;
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
            
            JOptionPane.showMessageDialog(null, "El alojamiento se ha insertado "
                    + "correctamente");
            
            connection.closeDB();
        }catch(SQLException ex){
            if(ex.getErrorCode() != 0)
                JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM alojamiento WHERE id = ?";
        
        try{
            ConnectionDB connection = new ConnectionDB();
            PreparedStatement pstm = connection.getConnection().prepareStatement(query);
            
            pstm.setInt(1, id);
         
            pstm.executeQuery().close();
            
            JOptionPane.showMessageDialog(null, "El alojamiento se ha borrado "
                    + "correctamente");
            connection.closeDB();
        }catch(SQLException ex){
            if(ex.getErrorCode() != 0)
                JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
    }
    
    public Object[] search(int id){
        String query = "Select id, establecimiento, "
                + "'C/' || (direccion).calle || ' número ' || (direccion).numero || ' ' || (direccion).pais || ' ' || (direccion).provincia, "
                + "tipo_establecimiento, categoria from alojamiento where id = ?";
        
        try{
            ConnectionDB connection = new ConnectionDB();
            PreparedStatement pstm = connection.getConnection().prepareStatement(query);

            pstm.setInt(1, id);
            
            ResultSet rs = pstm.executeQuery();
            
            Object[] housingObject = new Object[HOUSINGCOLUMNS];
            
            while(rs.next()){
                for(int i = 0; i < HOUSINGCOLUMNS; i++){
                    housingObject[i] = rs.getObject(i+1);
                }
            }
            
            pstm.close();
            connection.closeDB();            
            
            return housingObject;
        }catch(SQLException ex){
            if(ex.getErrorCode() != 0)
                JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
            
            return null;
        }
    }
    
}
