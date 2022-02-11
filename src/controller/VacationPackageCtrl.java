package controller;

import java.sql.Date;
import java.util.List;
import model.crud.VacationPackageCRUD;
import model.pojo.FlyPojo;
import model.pojo.VacationPackagePojo;

/**
 *
 * @author Tania Fariña González
 */

public class VacationPackageCtrl {
    private VacationPackageCRUD model = new VacationPackageCRUD();
    
    public void create(Date dateBegin, Date dateEnd, int housingId, String airportOrigin, String airportDestination, String[] airportLayover, String enterprise,
            String airportOriginReturn, String airportDestinationReturn, String[] airportLayoverReturn, String enterpriseReturn) {
        
        FlyPojo flyGoing = new FlyPojo(airportOrigin, airportDestination, airportLayover, enterprise, airportLayover.length+1);
        FlyPojo flyReturn = new FlyPojo(airportOriginReturn, airportDestinationReturn, airportLayoverReturn, enterpriseReturn, airportLayoverReturn.length+1);
        
        VacationPackagePojo vp = new VacationPackagePojo(dateBegin, dateEnd, housingId, flyGoing, flyReturn);
        model.create(vp);  
    }
    
    public List<Object[]> read() {
        return model.read();
    }

    public void update(int vpId, Date vpFlyBegin, Date vpFlyEnd, int housing) {
        String result = "UPDATE paquete_vacacional SET";
        result += " fecha_inicio = " + "'" + vpFlyBegin + "', ";
        result += " fecha_fin = " + "'" + vpFlyBegin + "', ";
        result += " alojamiento = " + "'" + housing + "'";
        result += " WHERE id = " + vpId;
        
        model.update(result);
    }

    public void delete(int id) {
        model.delete(id);
    }
    
    
    public Object[] search(int id){
        return model.search(id);
    }

}
