package com.example.user.e_leave;

/**
 * Created by USER on 27-09-2018.
 */
public class Leave {
    private int id;
    private String name,leaveid,desc;

    public Leave(int id, String name, String leaveid, String desc) {
        this.id = id;
        this.name = name;
        this.leaveid = leaveid;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeaveid() {
        return leaveid;
    }

    public void setLeaveid(String leaveid) {
        this.leaveid = leaveid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
