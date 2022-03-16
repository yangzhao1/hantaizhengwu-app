package com.example.gs.gonser.govenmentservice.interfaces;

/**
 * Created by yangzhao on 2018/5/5.
 */

public interface MyCallBack {
    //链接成功执行的方法
    void onSuccess(String json);//方法参数根据个人需求写，可以是字符串，也可以是输入流
    //链接失败执行的方法
    void onFailure(int code);//方法参数用int数据类型，相当于是一个标识
}
