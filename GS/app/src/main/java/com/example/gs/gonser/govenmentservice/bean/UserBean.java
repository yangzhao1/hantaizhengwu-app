package com.example.gs.gonser.govenmentservice.bean;

import java.io.Serializable;

/**
 * Created by yangzhao on 2018/5/10.
 * 用户信息
 */

public class UserBean implements Serializable{
    private String message;
    private String code;
    private LoginData data;

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

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }

    public class LoginData{
        private String id;
        private String username;
        private String certificate_Type;
        private String unicode;
        private String userName;
        private String password;
        private String officeId;
        private String unitName;
        private String fidentityId;
        private String mobilePhone;
        private String mobilephone;
        private String officeid;

        private String unitname;
        private String url;
        private String email;
        private String identityid;
        private String examinetype;
        private String paperstype;
        private String sex;
        private String usertype;

        public String getCertificate_Type() {
            return certificate_Type;
        }

        public void setCertificate_Type(String certificate_Type) {
            this.certificate_Type = certificate_Type;
        }

        public String getUnicode() {
            return unicode;
        }

        public void setUnicode(String unicode) {
            this.unicode = unicode;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getOfficeId() {
            return officeId;
        }

        public void setOfficeId(String officeId) {
            this.officeId = officeId;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getFidentityId() {
            return fidentityId;
        }

        public void setFidentityId(String fidentityId) {
            this.fidentityId = fidentityId;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public String getUsertype() {
            return usertype;
        }

        public void setUsertype(String usertype) {
            this.usertype = usertype;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getMobilephone() {
            return mobilephone;
        }

        public void setMobilephone(String mobilephone) {
            this.mobilephone = mobilephone;
        }

        public String getOfficeid() {
            return officeid;
        }

        public void setOfficeid(String officeid) {
            this.officeid = officeid;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIdentityid() {
            return identityid;
        }

        public void setIdentityid(String identityid) {
            this.identityid = identityid;
        }

        public String getExaminetype() {
            return examinetype;
        }

        public void setExaminetype(String examinetype) {
            this.examinetype = examinetype;
        }

        public String getPaperstype() {
            return paperstype;
        }

        public void setPaperstype(String paperstype) {
            this.paperstype = paperstype;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getUnitname() {
            return unitname;
        }

        public void setUnitname(String unitname) {
            this.unitname = unitname;
        }

        @Override
        public String toString() {
            return "LoginData{" +
                    "id='" + id + '\'' +
                    ", username='" + username + '\'' +
                    ", certificate_Type='" + certificate_Type + '\'' +
                    ", unicode='" + unicode + '\'' +
                    ", userName='" + userName + '\'' +
                    ", password='" + password + '\'' +
                    ", officeId='" + officeId + '\'' +
                    ", unitName='" + unitName + '\'' +
                    ", fidentityId='" + fidentityId + '\'' +
                    ", mobilePhone='" + mobilePhone + '\'' +
                    ", mobilephone='" + mobilephone + '\'' +
                    ", officeid='" + officeid + '\'' +
                    ", unitname='" + unitname + '\'' +
                    ", url='" + url + '\'' +
                    ", email='" + email + '\'' +
                    ", identityid='" + identityid + '\'' +
                    ", examinetype='" + examinetype + '\'' +
                    ", paperstype='" + paperstype + '\'' +
                    ", sex='" + sex + '\'' +
                    ", usertype='" + usertype + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
