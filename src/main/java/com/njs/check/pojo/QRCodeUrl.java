package com.njs.check.pojo;

public class QRCodeUrl {
    private Integer id;

    private String qrcodeUrl;

    private Integer applicationId;

    private String extra;

    public QRCodeUrl(Integer id, String qrcodeUrl, Integer applicationId) {
        this.id = id;
        this.qrcodeUrl = qrcodeUrl;
        this.applicationId = applicationId;
    }

    public QRCodeUrl(Integer id, String qrcodeUrl, Integer applicationId, String extra) {
        this.id = id;
        this.qrcodeUrl = qrcodeUrl;
        this.applicationId = applicationId;
        this.extra = extra;
    }

    public QRCodeUrl() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQrcodeUrl() {
        return qrcodeUrl;
    }

    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl == null ? null : qrcodeUrl.trim();
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra == null ? null : extra.trim();
    }
}