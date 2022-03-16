package com.example.gs.gonser.govenmentservice.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by yangzhao on 2018/6/19.
 */

public class HelpTopicImageBean implements Parcelable {

    private String url;

    public HelpTopicImageBean() {
    }

    public HelpTopicImageBean(Parcel in) {
        url = in.readString();
    }

    public static final Creator<HelpTopicImageBean> CREATOR = new Creator<HelpTopicImageBean>() {
        @Override
        public HelpTopicImageBean createFromParcel(Parcel in) {
            return new HelpTopicImageBean(in);
        }

        @Override
        public HelpTopicImageBean[] newArray(int size) {
            return new HelpTopicImageBean[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
    }
}
