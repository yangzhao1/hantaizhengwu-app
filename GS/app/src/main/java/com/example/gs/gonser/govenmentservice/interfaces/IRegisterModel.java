package com.example.gs.gonser.govenmentservice.interfaces;

import java.util.Map;

/**
 * Created by yangzhao on 2018/5/5.
 * 注册接口model
 */

public interface IRegisterModel {
    void goRegister(Map<String,String> map, MyCallBack myCallBack);
}
