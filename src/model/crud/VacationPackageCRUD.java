package model.crud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.ConnectionDB;
import model.pojo.VacationPackagePojo;

/**
 *
 * @author Tania Fariña González
 */

public class VacationPackageCRUD {
    private static final int VACATIONALPACKAGECOLUMNS = 6; 
    public void create(VacationPackagePojo vp) {
        String query = "INSERT INTO paquete_vacacional (fecha_inicio, fecha_fin, alojamiento, vuelo_ida, vuelo_fin) VALUES\n" +
"	(?, ?, ?, (?, ?, ?, ?, ?), (?, ?, ?, ?, ?))";
        
        try{
            ConnectionDB connection = new ConnectionDB();
            PreparedStatement pstm = connection.getConnection().prepareStatement(query);
            
            
            pstm.setString(1, null);
            pstm.setString(2, null);
            pstm.setInt(3, vp.getHousingId());
            
            pstm.setString(4, vp.getFlyGoing().getAirportOrigin());
            pstm.setString(5, vp.getFlyGoing().getAirportDestination());
            pstm.setString(6, vp.getFlyGoing().getAirportLayover());
            pstm.setString(7, vp.getFlyGoing().getEnterprise());
            pstm.setInt(8, vp.getFlyGoing().getFlyNumber());
            
            pstm.setString(9, vp.getFlyReturn().getAirportOrigin());
            pstm.setString(10, vp.getFlyReturn().getAirportDestination());
            pstm.setString(11, vp.getFlyReturn().getAirportLayover());
            pstm.setString(12, vp.getFlyReturn().getEnterprise());
            pstm.setInt(13, vp.getFlyReturn().getFlyNumber());
            
            pstm.executeQuery().close();
            
            JOptionPane.showMessageDialog(null, "El paquete vacacional se ha insertado "
                    + "correctamente");
            
            connection.closeDB();
        }catch(SQLException ex){
            if(ex.getErrorCode() != 0)
                JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
    }
    
    public List<Object[]> read() {
        String query = "select id, fecha_inicio, fecha_fin, alojamiento, "
                + "(vuelo_ida).aeropuerto_origen || '/' || (vuelo_ida).aeropuerto_destino, "
                + "(vuelo_fin).aeropuerto_origen || '/' || (vuelo_fin).aeropuerto_destino from paquete_vacacional ";
        
        List <Object[]> vacationalPackageList = new ArrayList<>();
        
        try{
            ConnectionDB connection = new ConnectionDB();
            Statement stm = connection.getConnection().createStatement();
            
            ResultSet rs = stm.executeQuery(query);
            
            while(rs.next()){
                Object[] vacationalPackageObject = new Object[VACATIONALPACKAGECOLUMNS];
                
                for(int i = 0; i < VACATIONALPACKAGECOLUMNS; i++){
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
            
            JOptionPane.showMessageDialog(null, "El paquete vacacional se ha insertado "
                    + "correctamente");
            
            connection.closeDB();
        }catch(SQLException ex){
            if(ex.getErrorCode() != 0)
                JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM paquete_vacacional WHERE id = ?";
        
        try{
            ConnectionDB connection = new ConnectionDB();
            PreparedStatement pstm = connection.getConnection().prepareStatement(query);
            
            pstm.setInt(1, id);
         
            pstm.executeQuery().close();
            
            JOptionPane.showMessageDialog(null, "El paquete vacacional se ha borrado "
                    + "correctamente");
            connection.closeDB();
        }catch(SQLException ex){
            if(ex.getErrorCode() != 0)
                JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
        }
    }
    
    public Object[] search(int id){
        String query = "select id, fecha_inicio, fecha_fin, alojamiento, "
                + "(vuelo_ida).aeropuerto_origen || '/' || (vuelo_ida).aeropuerto_destino, "
                + "(vuelo_fin).aeropuerto_origen || '/' || (vuelo_fin).aeropuerto_destino from paquete_vacacional WHERE id = ?";
        
        try{
            ConnectionDB connection = new ConnectionDB();
            PreparedStatement pstm = connection.getConnection().prepareStatement(query);

            pstm.setInt(1, id);
            
            ResultSet rs = pstm.executeQuery();
            
            Object[] vacationalPackageObject = new Object[1];
            vacationalPackageObject[0] = rs.getObject(1);

            pstm.close();
            connection.closeDB();            
            
            return vacationalPackageObject;
        }catch(SQLException ex){
            if(ex.getErrorCode() != 0)
                JOptionPane.showMessageDialog(null, "ERROR: " + ex.getMessage());
            
            return null;
        }
    }
}
