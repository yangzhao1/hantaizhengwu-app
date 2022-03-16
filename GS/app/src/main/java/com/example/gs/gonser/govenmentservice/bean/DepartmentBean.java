package com.example.gs.gonser.govenmentservice.bean;

import java.util.List;

/**
 * Created by yangzhao on 2018/5/29.
 */

public class DepartmentBean {

    private String message;
    private String code;
    private List<DepartData> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DepartData> getData() {
        return data;
    }

    public void setData(List<DepartData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DepartmentBean{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DepartData{
        String id;
        String departname;
        String parentdepartid;
        String orgCode;
        String orgType;
        String complaints;
        String dpatDtoList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDepartname() {
            return departname;
        }

        public void setDepartname(String departname) {
            this.departname = departname;
        }

        public String getParentdepartid() {
            return parentdepartid;
        }

        public void setParentdepartid(String parentdepartid) {
            this.parentdepartid = parentdepartid;
        }

        public String getOrgCode() {
            return orgCode;
        }

        public void setOrgCode(String orgCode) {
            this.orgCode = orgCode;
        }

        public String getOrgType() {
            return orgType;
        }

        public void setOrgType(String orgType) {
            this.orgType = orgType;
        }

        public String getComplaints() {
            return complaints;
        }

        public void setComplaints(String complaints) {
            this.complaints = complaints;
        }

        public String getDpatDtoList() {
            return dpatDtoList;
        }

        public void setDpatDtoList(String dpatDtoList) {
            this.dpatDtoList = dpatDtoList;
        }

        @Override
        public String toString() {
            return "DepartData{" +
                    "id='" + id + '\'' +
                    ", departname='" + departname + '\'' +
                    ", parentdepartid='" + parentdepartid + '\'' +
                    ", orgCode='" + orgCode + '\'' +
                    ", orgType='" + orgType + '\'' +
                    ", complaints='" + complaints + '\'' +
                    ", dpatDtoList='" + dpatDtoList + '\'' +
                    '}';
        }
    }

}
