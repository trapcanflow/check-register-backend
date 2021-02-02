package com.njs.check.pojo;

import java.util.Date;

public class PdfUrl {
    private Integer id;

    private String wordUrl;

    private Date createTime;

    private Integer applicationId;

    private String extra;

    public PdfUrl(Integer id, String wordUrl, Date createTime, Integer applicationId) {
        this.id = id;
        this.wordUrl = wordUrl;
        this.createTime = createTime;
        this.applicationId = applicationId;
    }

    public PdfUrl(Integer id, String wordUrl, Date createTime, Integer applicationId, String extra) {
        this.id = id;
        this.wordUrl = wordUrl;
        this.createTime = createTime;
        this.applicationId = applicationId;
        this.extra = extra;
    }

    public PdfUrl() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWordUrl() {
        return wordUrl;
    }

    public void setWordUrl(String wordUrl) {
        this.wordUrl = wordUrl == null ? null : wordUrl.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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