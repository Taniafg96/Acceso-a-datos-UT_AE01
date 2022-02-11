package controller;

import java.util.List;
import model.crud.ClientCRUD;
import model.pojo.ClientPojo;
import model.pojo.DirectionPojo;
import model.pojo.HousingPojo;

/**
 *
 * @author Tania Fariña González
 */

public class ClientCtrl {
    private ClientCRUD model = new ClientCRUD();
    
    
    public void create(String clientDni, String clientName, String clientLastName, String[] clientPhone, int clientNumber, String clientStreet, String clientCountry, String clientProvince, int vpId) {
        DirectionPojo direction = new DirectionPojo(clientNumber, clientStreet, clientCountry, clientProvince);
        ClientPojo client = new ClientPojo(clientDni, clientName, clientLastName, clientPhone, direction, vpId);
        model.create(client);
    }

    public List<Object[]> read() {
        return model.read();
    }

    public void update(String clientDni, String clientName, String clientLastName, int clientVpId){
        String result = "UPDATE cliente SET";
        if(!clientDni.isEmpty()) result += " dni = " + "'" + clientDni + "', ";
        result += " nombre = " + "'" + clientName + "', ";
        result += " apellidos = '" +  clientLastName + "', ";
        result += " paquete_vacacional = " +  clientVpId;
        result += " WHERE dni = '" + clientDni + "'";
        
        model.update(result);
    }

    public void delete(String id) {
        model.delete(id);
    }
    
    public Object[] search(String dni){
        return model.search(dni);
    }
    
    public String consult(String dni){
        return model.consult(dni);
    }
}
