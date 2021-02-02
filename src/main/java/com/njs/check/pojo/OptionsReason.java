package com.njs.check.pojo;

public class OptionsReason {
    private Integer id;

    private String reasonName;

    private String extra;

    public OptionsReason(Integer id, String reasonName) {
        this.id = id;
        this.reasonName = reasonName;
    }

    public OptionsReason(Integer id, String reasonName, String extra) {
        this.id = id;
        this.reasonName = reasonName;
        this.extra = extra;
    }

    public OptionsReason() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReasonName() {
        return reasonName;
    }

    public void setReasonName(String reasonName) {
        this.reasonName = reasonName == null ? null : reasonName.trim();
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra == null ? null : extra.trim();
    }
}