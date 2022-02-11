/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pojo;

/**
 *
 * @author Usuario
 */
public class HousingPojo {
    private int id;
    private String house;
    private DirectionPojo direction;
    private String housingType;
    private int category;

    public HousingPojo(String house, DirectionPojo direction, String housingType, int category) {
        this.house = house;
        this.direction = direction;
        this.housingType = housingType;
        this.category = category;
    }

    public HousingPojo(int id, String house, String housingType, int category) {
        this.id = id;
        this.house = house;
        this.housingType = housingType;
        this.category = category;
    }
    
    public int getId() {
        return id;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public DirectionPojo getDirection() {
        return direction;
    }

    public void setDirection(DirectionPojo direction) {
        this.direction = direction;
    }

    public String getHousingType() {
        return housingType;
    }

    public void setHousingType(String housingType) {
        this.housingType = housingType;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
    
    
}
