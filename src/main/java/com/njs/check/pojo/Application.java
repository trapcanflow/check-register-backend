package com.njs.check.pojo;

import java.util.Date;

public class Application {
    private Integer id;

    private Date applyTime;

    private Integer applicantId;

    private String applicant;

    private String position;

    private String accompany;

    private String reason;

    private String fundsFrom;

    private Date startTime;

    private Date endTime;

    private String departure;

    private String destination;

    private String transport;

    private String transportBeyond;

    private String advise;

    private String approval;

    private Integer status;

    private Integer adviseId;

    private Integer approvalId;

    private Date adviseTime;

    private Date approvalTime;

    public Application(Integer id, Date applyTime, Integer applicantId, String applicant, String position, String accompany, String reason, String fundsFrom, Date startTime, Date endTime, String departure, String destination, String transport, String transportBeyond, String advise, String approval, Integer status, Integer adviseId, Integer approvalId, Date adviseTime, Date approvalTime) {
        this.id = id;
        this.applyTime = applyTime;
        this.applicantId = applicantId;
        this.applicant = applicant;
        this.position = position;
        this.accompany = accompany;
        this.reason = reason;
        this.fundsFrom = fundsFrom;
        this.startTime = startTime;
        this.endTime = endTime;
        this.departure = departure;
        this.destination = destination;
        this.transport = transport;
        this.transportBeyond = transportBeyond;
        this.advise = advise;
        this.approval = approval;
        this.status = status;
        this.adviseId = adviseId;
        this.approvalId = approvalId;
        this.adviseTime = adviseTime;
        this.approvalTime = approvalTime;
    }

    public Application() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Integer applicantId) {
        this.applicantId = applicantId;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant == null ? null : applicant.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getAccompany() {
        return accompany;
    }

    public void setAccompany(String accompany) {
        this.accompany = accompany == null ? null : accompany.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getFundsFrom() {
        return fundsFrom;
    }

    public void setFundsFrom(String fundsFrom) {
        this.fundsFrom = fundsFrom == null ? null : fundsFrom.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure == null ? null : departure.trim();
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination == null ? null : destination.trim();
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport == null ? null : transport.trim();
    }

    public String getTransportBeyond() {
        return transportBeyond;
    }

    public void setTransportBeyond(String transportBeyond) {
        this.transportBeyond = transportBeyond == null ? null : transportBeyond.trim();
    }

    public String getAdvise() {
        return advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise == null ? null : advise.trim();
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval == null ? null : approval.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAdviseId() {
        return adviseId;
    }

    public void setAdviseId(Integer adviseId) {
        this.adviseId = adviseId;
    }

    public Integer getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(Integer approvalId) {
        this.approvalId = approvalId;
    }

    public Date getAdviseTime() {
        return adviseTime;
    }

    public void setAdviseTime(Date adviseTime) {
        this.adviseTime = adviseTime;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }
}