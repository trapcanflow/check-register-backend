package com.njs.check.pojo;

public class SecondDep {
    private Integer id;

    private String secondDepName;

    private Integer firstDepId;

    public SecondDep(Integer id, String secondDepName, Integer firstDepId) {
        this.id = id;
        this.secondDepName = secondDepName;
        this.firstDepId = firstDepId;
    }

    public SecondDep() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSecondDepName() {
        return secondDepName;
    }

    public void setSecondDepName(String secondDepName) {
        this.secondDepName = secondDepName == null ? null : secondDepName.trim();
    }

    public Integer getFirstDepId() {
        return firstDepId;
    }

    public void setFirstDepId(Integer firstDepId) {
        this.firstDepId = firstDepId;
    }
}