package model.pojo;

import java.sql.Date;

/**
 *
 * @author Tania Fariña González
 */

public class VacationPackagePojo {
    private int id;
    private Date dateBegin;
    private Date dateEnd;
    private int housingId;
    private FlyPojo flyGoing;
    private FlyPojo flyReturn;

    public VacationPackagePojo(Date dateBegin, Date dateEnd, int housingId, FlyPojo flyGoing, FlyPojo flyReturn) {
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.housingId = housingId;
        this.flyGoing = flyGoing;
        this.flyReturn = flyReturn;
    }

    public int getId() {
        return id;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getHousingId() {
        return housingId;
    }

    public void setHousingId(int housingId) {
        this.housingId = housingId;
    }

    public FlyPojo getFlyGoing() {
        return flyGoing;
    }

    public void setFlyGoing(FlyPojo flyGoing) {
        this.flyGoing = flyGoing;
    }

    public FlyPojo getFlyReturn() {
        return flyReturn;
    }

    public void setFlyReturn(FlyPojo flyReturn) {
        this.flyReturn = flyReturn;
    }
    
    
}
