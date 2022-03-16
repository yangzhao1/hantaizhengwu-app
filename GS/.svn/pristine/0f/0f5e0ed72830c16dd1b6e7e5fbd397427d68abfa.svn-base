package com.example.gs.gonser.govenmentservice.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yangzhao on 2018/5/19.
 * 政策解读实体类
 */

public class PolicyReadBean implements Parcelable{
    private String id;
    private String url;
    private String title;
    private String content;
    private String date;

    public PolicyReadBean(){

    }

    protected PolicyReadBean(Parcel in) {
        id = in.readString();
        url = in.readString();
        title = in.readString();
        content = in.readString();
        date = in.readString();
    }

    public static final Creator<PolicyReadBean> CREATOR = new Creator<PolicyReadBean>() {
        @Override
        public PolicyReadBean createFromParcel(Parcel in) {
            return new PolicyReadBean(in);
        }

        @Override
        public PolicyReadBean[] newArray(int size) {
            return new PolicyReadBean[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(url);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(date);
    }
}
