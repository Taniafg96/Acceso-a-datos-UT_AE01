/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Utils {
        
    public Object[] read(int housingColumns) {
        String query = "select direccion_formato(direccion) from alojamiento";
        Object[] housingList = new Object[housingColumns];
        
        try{
            ConnectionDB connection = new ConnectionDB();
            Statement stm = connection.getConnection().createStatement();
            
            ResultSet rs = stm.executeQuery(query);
            
            int i = 0;
            while(rs.next()){
                housingList[i++] = rs.getObject(1);
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
}
