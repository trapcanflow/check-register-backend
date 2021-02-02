package com.njs.check.pojo;

public class OptionsFunds {
    private Integer id;

    private String fundsFromName;

    private String extra;

    public OptionsFunds(Integer id, String fundsFromName) {
        this.id = id;
        this.fundsFromName = fundsFromName;
    }

    public OptionsFunds(Integer id, String fundsFromName, String extra) {
        this.id = id;
        this.fundsFromName = fundsFromName;
        this.extra = extra;
    }

    public OptionsFunds() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFundsFromName() {
        return fundsFromName;
    }

    public void setFundsFromName(String fundsFromName) {
        this.fundsFromName = fundsFromName == null ? null : fundsFromName.trim();
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra == null ? null : extra.trim();
    }
}