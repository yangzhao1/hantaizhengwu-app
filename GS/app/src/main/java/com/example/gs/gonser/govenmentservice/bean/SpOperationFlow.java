package com.example.gs.gonser.govenmentservice.bean;


import java.util.List;

public class SpOperationFlow {
    private String id;

    private String userId;

    private String itemId;

    private String dept_of_power;//部门id

    private String departId;

    private String bidNumber;

    private String businessTheme;

    private String itemName;

    private String isUp;

    private String isCorrected;

    private String linkType;

    private String acceptDate;

    private String promiseDate;

    private String createDate;

    private String updateDate;

    private String createName;

    private String updateName;

    private String assUserid;

    private List<SpMaterialInformation> spMaterialInformation;

    private List<SpCheck> spChecks;

    private String createDate1;//搜索时间1

    private String createDate2;//搜索时间2

    private String correctedType;//补齐补正状态

    private String complaints;

    public String getFromEntryId() {
        return fromEntryId;
    }

    public void setFromEntryId(String fromEntryId) {
        this.fromEntryId = fromEntryId;
    }

    private String fromEntryId;

    public List<SpCheck> getSpChecks() {
        return spChecks;
    }

    public void setSpChecks(List<SpCheck> spChecks) {
        this.spChecks = spChecks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getBidNumber() {
        return bidNumber;
    }

    public void setBidNumber(String bidNumber) {
        this.bidNumber = bidNumber;
    }

    public String getBusinessTheme() {
        return businessTheme;
    }

    public void setBusinessTheme(String businessTheme) {
        this.businessTheme = businessTheme;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getIsUp() {
        return isUp;
    }

    public void setIsUp(String isUp) {
        this.isUp = isUp;
    }

    public String getIsCorrected() {
        return isCorrected;
    }

    public void setIsCorrected(String isCorrected) {
        this.isCorrected = isCorrected;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(String acceptDate) {
        this.acceptDate = acceptDate;
    }

    public String getPromiseDate() {
        return promiseDate;
    }

    public void setPromiseDate(String promiseDate) {
        this.promiseDate = promiseDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public List<SpMaterialInformation> getSpMaterialInformation() {
        return spMaterialInformation;
    }

    public void setSpMaterialInformation(List<SpMaterialInformation> spMaterialInformation) {
        this.spMaterialInformation = spMaterialInformation;
    }

    public String getDept_of_power() {
        return dept_of_power;
    }

    public void setDept_of_power(String dept_of_power) {
        this.dept_of_power = dept_of_power;
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public String getCreateDate1() {
        return createDate1;
    }

    public void setCreateDate1(String createDate1) {
        this.createDate1 = createDate1;
    }

    public String getCreateDate2() {
        return createDate2;
    }

    public void setCreateDate2(String createDate2) {
        this.createDate2 = createDate2;
    }

    public String getCorrectedType() {
        return correctedType;
    }

    public void setCorrectedType(String correctedType) {
        this.correctedType = correctedType;
    }

    public String getAssUserid() {
        return assUserid;
    }

    public void setAssUserid(String assUserid) {
        this.assUserid = assUserid;
    }

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }
}