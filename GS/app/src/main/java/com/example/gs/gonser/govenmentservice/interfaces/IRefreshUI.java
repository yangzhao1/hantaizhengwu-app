package com.example.gs.gonser.govenmentservice.interfaces;

/**
 * Created by yangzhao on 2018/5/5.
 */

public interface IRefreshUI {
    void success(String message,String json);
    void fails(String message,int code);
}
