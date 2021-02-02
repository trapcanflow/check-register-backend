package com.njs.check.pojo;

public class SecondPosition {
    private Integer id;

    private Integer firstPositionId;

    private String secondPositionName;

    public SecondPosition(Integer id, Integer firstPositionId, String secondPositionName) {
        this.id = id;
        this.firstPositionId = firstPositionId;
        this.secondPositionName = secondPositionName;
    }

    public SecondPosition() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFirstPositionId() {
        return firstPositionId;
    }

    public void setFirstPositionId(Integer firstPositionId) {
        this.firstPositionId = firstPositionId;
    }

    public String getSecondPositionName() {
        return secondPositionName;
    }

    public void setSecondPositionName(String secondPositionName) {
        this.secondPositionName = secondPositionName == null ? null : secondPositionName.trim();
    }
}