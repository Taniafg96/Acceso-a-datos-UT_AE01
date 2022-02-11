package controller;

import java.util.List;
import model.Utils;
import model.crud.HousingCRUD;
import model.pojo.DirectionPojo;
import model.pojo.HousingPojo;

/**
 *
 * @author Tania Fariña González
 */

public class HousingCtrl { 
    private HousingCRUD model = new HousingCRUD();
    
    public void create(String house, int number, String street, String country, 
            String Province, String housingType, int category) {
        
        DirectionPojo direction = new DirectionPojo(number, street, country, Province);          
        HousingPojo housing = new HousingPojo(house, direction, housingType, category);
        
        model.create(housing);
    }
    
    public List<Object[]> read() {
        return model.read();
    }

    public void update(int id, String house, String houseType, int category) {
        String result = "UPDATE alojamiento SET";
        if(!house.isEmpty()) result += " establecimiento = " + "'" + house + "', ";
        result += " tipo_establecimiento = " + "'" + houseType + "', ";
        result += " categoria = " +  category;
        result += " WHERE id = " + id;
        
        model.update(result);
    }

    public void delete(int id) {
        model.delete(id);
    }
    
    public Object[] search(int id){
        return model.search(id);
    }
}
