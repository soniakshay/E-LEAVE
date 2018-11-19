package com.example.user.e_leave;

/**
 * Created by USER on 14-10-2018.
 */
public class statuspozo {
    private String name,todate,fromdate,desc,designation,status;

    public statuspozo(String name, String todate, String fromdate, String desc, String designation, String status) {
        this.name = name;
        this.todate = todate;
        this.fromdate = fromdate;
        this.desc = desc;
        this.designation = designation;
        this.status=status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
