package com.njs.check.pojo;

public class SummerUrl {
    private Integer id;

    private Integer applicationId;

    private String fileUrl;

    private String title;

    private String fileType;

    public SummerUrl(Integer id, Integer applicationId, String fileUrl, String title, String fileType) {
        this.id = id;
        this.applicationId = applicationId;
        this.fileUrl = fileUrl;
        this.title = title;
        this.fileType = fileType;
    }

    public SummerUrl() {
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

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }
}