package com.njs.check.pojo;

import java.util.Date;

public class User {
    private Integer id;

    private Integer gender;

    private String username;

    private String name;

    private String phoneNum;

    private String wechatAccount;

    private String wechatId;

    private Date regTime;

    private String indentityId;

    private String job;

    private Integer firstPositionId;

    private Integer secondPositionId;

    private Integer firstDepId;

    private Integer secondDepId;

    private Integer roleId;

    private String wokeCurAdd;

    private Integer status;

    public User(Integer id, Integer gender, String username, String name, String phoneNum, String wechatAccount, String wechatId, Date regTime, String indentityId, String job, Integer firstPositionId, Integer secondPositionId, Integer firstDepId, Integer secondDepId, Integer roleId, String wokeCurAdd, Integer status) {
        this.id = id;
        this.gender = gender;
        this.username = username;
        this.name = name;
        this.phoneNum = phoneNum;
        this.wechatAccount = wechatAccount;
        this.wechatId = wechatId;
        this.regTime = regTime;
        this.indentityId = indentityId;
        this.job = job;
        this.firstPositionId = firstPositionId;
        this.secondPositionId = secondPositionId;
        this.firstDepId = firstDepId;
        this.secondDepId = secondDepId;
        this.roleId = roleId;
        this.wokeCurAdd = wokeCurAdd;
        this.status = status;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getWechatAccount() {
        return wechatAccount;
    }

    public void setWechatAccount(String wechatAccount) {
        this.wechatAccount = wechatAccount == null ? null : wechatAccount.trim();
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId == null ? null : wechatId.trim();
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getIndentityId() {
        return indentityId;
    }

    public void setIndentityId(String indentityId) {
        this.indentityId = indentityId == null ? null : indentityId.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public Integer getFirstPositionId() {
        return firstPositionId;
    }

    public void setFirstPositionId(Integer firstPositionId) {
        this.firstPositionId = firstPositionId;
    }

    public Integer getSecondPositionId() {
        return secondPositionId;
    }

    public void setSecondPositionId(Integer secondPositionId) {
        this.secondPositionId = secondPositionId;
    }

    public Integer getFirstDepId() {
        return firstDepId;
    }

    public void setFirstDepId(Integer firstDepId) {
        this.firstDepId = firstDepId;
    }

    public Integer getSecondDepId() {
        return secondDepId;
    }

    public void setSecondDepId(Integer secondDepId) {
        this.secondDepId = secondDepId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getWokeCurAdd() {
        return wokeCurAdd;
    }

    public void setWokeCurAdd(String wokeCurAdd) {
        this.wokeCurAdd = wokeCurAdd == null ? null : wokeCurAdd.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}