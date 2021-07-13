package com.njs.check.pojo;

import java.util.Date;

public class Summer {
    private Integer id;

    private Integer applicationId;

    private String text;

    private Date createTime;

    private Date updateTime;

    private String title;

    private Integer userId;

    public Summer(Integer id, Integer applicationId, String text, Date createTime, Date updateTime, String title, Integer userId) {
        this.id = id;
        this.applicationId = applicationId;
        this.text = text;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.title = title;
        this.userId = userId;
    }

    public Summer() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}