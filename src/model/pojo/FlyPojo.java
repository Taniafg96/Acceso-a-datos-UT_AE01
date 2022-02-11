package model.pojo;

/**
 *
 * @author Tania Fariña González
 */

public class FlyPojo {
    private String airportOrigin;
    private String airportDestination;
    private String[] airportLayover;
    private String enterprise;
    private int flyNumber;

    public FlyPojo(String airportOrigin, String airportDestination, String[] airportLayover, String enterprise, int flyNumber) {
        this.airportOrigin = airportOrigin;
        this.airportDestination = airportDestination;
        this.airportLayover = airportLayover;
        this.enterprise = enterprise;
        this.flyNumber = flyNumber;
    }

    public FlyPojo(String airportOrigin, String airportDestination, String[] airportLayover, String enterprise) {
        this.airportOrigin = airportOrigin;
        this.airportDestination = airportDestination;
        this.airportLayover = airportLayover;
        this.enterprise = enterprise;
        flyNumber = airportLayover.length + 1;
    }

    public String getAirportOrigin() {
        return airportOrigin;
    }

    public void setAirportOrigin(String airportOrigin) {
        this.airportOrigin = airportOrigin;
    }

    public String getAirportDestination() {
        return airportDestination;
    }

    public void setAirportDestination(String airportDestination) {
        this.airportDestination = airportDestination;
    }

    public String getAirportLayover() {
        int empty = 0;
        String airportLayoverString = "ARRAY[";
        for(String i : airportLayover){
            if(!i.isEmpty()){
               if(empty != 0) airportLayoverString += ", '" + i + "'";
               else{
                   airportLayoverString += "'" + i + "'";
                   empty++;
               }
            }
                    }
        airportLayoverString += "]";
        if(airportLayoverString.equals("ARRAY[]")) return "NULL";
        else return airportLayoverString;
    }

    public void setAirportLayover(String[] airportLayover) {
        this.airportLayover = airportLayover;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public int getFlyNumber() {
        return flyNumber;
    }

    public void setFlyNumber(int flyNumber) {
        this.flyNumber = flyNumber;
    }
    
    
}
