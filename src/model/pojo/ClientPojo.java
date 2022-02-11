package model.pojo;

/**
 *
 * @author Tania Fariña González
 */

public class ClientPojo {
    private String dni;
    private String name;
    private String lastName;
    private String[] phone;
    private DirectionPojo direction;
    private int vacationPackage;

    public ClientPojo(String dni, String name, String lastName, String[] phone, DirectionPojo direction, int vacationPackage) {
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.direction = direction;
        this.vacationPackage = vacationPackage;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        int empty = 0;
        String phoneString = "ARRAY[";
        for(String i : phone){
            if(!i.isEmpty()){
               if(empty != 0) phoneString += ", '" + i + "'";
               else{
                   phoneString += "'" + i + "'";
                   empty++;
               }
            }
        }
        phoneString += "]";
        if(phoneString.equals("ARRAY[]")) return "NULL";
        else return phoneString;
    }

    public void setPhone(String[] phone) {
        this.phone = phone;
    }

    public DirectionPojo getDirection() {
        return direction;
    }

    public void setDirection(DirectionPojo direction) {
        this.direction = direction;
    }

    public int getVacationPackage() {
        return vacationPackage;
    }

    public void setVacationPackage(int vacationPackage) {
        this.vacationPackage = vacationPackage;
    }
    
    
}
