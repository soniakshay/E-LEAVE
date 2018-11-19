package com.example.user.e_leave;

/**
 * Created by USER on 14-10-2018.
 */
public class leavetoday {
    private String name,todate,fromdate,desc,designation;

    public leavetoday(String name, String todate, String fromdate, String desc, String designation) {
        this.name = name;
        this.todate = todate;
        this.fromdate = fromdate;
        this.desc = desc;
        this.designation = designation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
