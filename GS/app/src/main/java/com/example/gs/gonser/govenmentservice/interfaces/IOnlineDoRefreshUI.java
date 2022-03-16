package com.example.gs.gonser.govenmentservice.interfaces;

/**
 * Created by yangzhao on 2018/5/28.
 * 在线办理的刷新接口
 */

public interface IOnlineDoRefreshUI {
    void readIKnowSuccess(String json);
    void readIKnowFail(int code);
    void getItemInfoSuccess(String json);
    void getItemInfoFail(int code);
    void getItemHtmlDataSuccess(String json);
    void getItemHtmlDataFail(int code);
}
