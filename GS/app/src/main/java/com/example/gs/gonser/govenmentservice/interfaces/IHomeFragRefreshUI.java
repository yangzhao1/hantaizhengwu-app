package com.example.gs.gonser.govenmentservice.interfaces;

/**
 * Created by yangzhao on 2018/5/28.
 * 首页的刷新接口
 */

public interface IHomeFragRefreshUI {
    void noticeSuccess(String json);
    void noticeFail(int code);
    void getImageSuccess(String json);
    void getImageFail(int code);
}
