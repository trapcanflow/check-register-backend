package com.njs.check.pojo;

public class OptionsBeyond {
    private Integer id;

    private String transportBeyond;

    private String extra;

    public OptionsBeyond(Integer id, String transportBeyond) {
        this.id = id;
        this.transportBeyond = transportBeyond;
    }

    public OptionsBeyond(Integer id, String transportBeyond, String extra) {
        this.id = id;
        this.transportBeyond = transportBeyond;
        this.extra = extra;
    }

    public OptionsBeyond() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransportBeyond() {
        return transportBeyond;
    }

    public void setTransportBeyond(String transportBeyond) {
        this.transportBeyond = transportBeyond == null ? null : transportBeyond.trim();
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra == null ? null : extra.trim();
    }
}