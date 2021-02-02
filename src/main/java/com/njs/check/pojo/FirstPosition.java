package com.njs.check.pojo;

public class FirstPosition {
    private Integer id;

    private String positionName;

    public FirstPosition(Integer id, String positionName) {
        this.id = id;
        this.positionName = positionName;
    }

    public FirstPosition() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName == null ? null : positionName.trim();
    }
}