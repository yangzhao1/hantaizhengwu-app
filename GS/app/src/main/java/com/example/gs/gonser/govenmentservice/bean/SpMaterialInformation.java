package com.example.gs.gonser.govenmentservice.bean;

import java.util.List;

/**
 *sp_material_information
 * 上报材料信息
 * */

public class SpMaterialInformation {
    private String id;

    private String materialName;//材料名称

    private String flowId;//流程主键

    private String submitType;//提交方式(0窗口提交)
    private String submitTypes;//提交方式(0窗口提交)

    private String appOrder;//排序

    private String createDate;

    private String updateDate;

    private String createName;

    private String updateName;

    private List<SpUpload> spUploads;

    public List<SpUpload> getSpUploads() {
        return spUploads;
    }

    public void setSpUploads(List<SpUpload> spUploads) {
        this.spUploads = spUploads;
    }

    public String getSubmitTypes() {
        return submitTypes;
    }

    public void setSubmitTypes(String submitTypes) {
        this.submitTypes = submitTypes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getSubmitType() {
        return submitType;
    }

    public void setSubmitType(String submitType) {
        this.submitType = submitType;
    }

    public String getAppOrder() {
        return appOrder;
    }

    public void setAppOrder(String appOrder) {
        this.appOrder = appOrder;
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
}