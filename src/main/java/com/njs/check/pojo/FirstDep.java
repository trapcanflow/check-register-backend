package com.njs.check.pojo;

public class FirstDep {
    private Integer id;

    private String firstDepName;

    public FirstDep(Integer id, String firstDepName) {
        this.id = id;
        this.firstDepName = firstDepName;
    }

    public FirstDep() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstDepName() {
        return firstDepName;
    }

    public void setFirstDepName(String firstDepName) {
        this.firstDepName = firstDepName == null ? null : firstDepName.trim();
    }
}