package com.njs.check.pojo;

public class OptionsTransport {
    private Integer id;

    private String transportName;

    private String extra;

    public OptionsTransport(Integer id, String transportName) {
        this.id = id;
        this.transportName = transportName;
    }

    public OptionsTransport(Integer id, String transportName, String extra) {
        this.id = id;
        this.transportName = transportName;
        this.extra = extra;
    }

    public OptionsTransport() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransportName() {
        return transportName;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName == null ? null : transportName.trim();
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra == null ? null : extra.trim();
    }
}