package model.pojo;

/**
 *
 * @author Tania Fariña González
 */

public class DirectionPojo {
    private int number;
    private String street;
    private String country;
    private String province;

    public DirectionPojo(int number, String street, String country, String province) {
        this.number = number;
        this.street = street;
        this.country = country;
        this.province = province;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    
    
}
