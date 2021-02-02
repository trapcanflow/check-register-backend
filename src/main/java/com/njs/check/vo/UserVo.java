package com.njs.check.vo;


import com.njs.check.pojo.User;

public class UserVo extends User {

     private String firstDepName;

     private String secondDepName;

     private String firstPosition;

     private String secondPosition;



    public String getFirstPosition() {
        return firstPosition;
    }

    public void setFirstPosition(String firstPosition) {
        this.firstPosition = firstPosition;
    }

    public String getSecondPosition() {
        return secondPosition;
    }

    public void setSecondPosition(String secondPosition) {
        this.secondPosition = secondPosition;
    }

    public String getFirstDepName() {
        return firstDepName;
    }

    public void setFirstDepName(String firstDepName) {
        this.firstDepName = firstDepName;
    }

    public String getSecondDepName() {
        return secondDepName;
    }

    public void setSecondDepName(String secondDepName) {
        this.secondDepName = secondDepName;
    }


}
