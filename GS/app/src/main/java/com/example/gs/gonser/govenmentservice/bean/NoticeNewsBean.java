package com.example.gs.gonser.govenmentservice.bean;

/**
 * Created by yangzhao on 2018/5/7.
 * 通知公告实体类
 */

public class NoticeNewsBean {
    private String id;
    private String title;
    private String content;
    private String imageUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
