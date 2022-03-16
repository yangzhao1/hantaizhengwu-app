package com.example.gs.gonser.govenmentservice.bean;

import android.support.annotation.NonNull;

/**
 *sp_upload
 * 附件表
 * */
public class SpUpload implements Comparable<SpUpload>{
    private String id;

    private String parentId;//父id(来自于各上传模块)

    private String url;//上传路径

    private String oldName;//原文件名

    private String newName;//现文件名

    private String uploadId;//上传文件返回iD

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    private String fileSize;//大小

    private String uploadDate;//上传时间

    private String submitType;//提交方式

    private String materialType;//类型

    private Integer pos ;//类型

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    private String linkType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getSubmitType() {
        return submitType;
    }

    public void setSubmitType(String submitType) {
        this.submitType = submitType;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    @Override
    public int compareTo(@NonNull SpUpload o) {
        return this.pos.compareTo(o.getPos());
    }
}