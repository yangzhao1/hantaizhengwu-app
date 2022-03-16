package com.example.gs.gonser.govenmentservice.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by yangzhao on 2018/5/18.
 */

public class DoThingBean extends ArrayList<Parcelable> implements Parcelable{

    private String id;
    private String orgname;
    private String thing;
    private String code;
    private String startTime;
    private String endTime;
    private String status;

    public DoThingBean(){

    }

    protected DoThingBean(Parcel in) {
        id = in.readString();
        orgname = in.readString();
        thing = in.readString();
        code = in.readString();
        startTime = in.readString();
        endTime = in.readString();
        status = in.readString();
    }

    public static final Creator<DoThingBean> CREATOR = new Creator<DoThingBean>() {
        @Override
        public DoThingBean createFromParcel(Parcel in) {
            return new DoThingBean(in);
        }

        @Override
        public DoThingBean[] newArray(int size) {
            return new DoThingBean[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getThing() {
        return thing;
    }

    public void setThing(String thing) {
        this.thing = thing;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(orgname);
        dest.writeString(thing);
        dest.writeString(code);
        dest.writeString(startTime);
        dest.writeString(endTime);
        dest.writeString(status);
    }
}
