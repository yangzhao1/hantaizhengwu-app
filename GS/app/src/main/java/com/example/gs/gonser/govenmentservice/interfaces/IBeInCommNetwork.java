package com.example.gs.gonser.govenmentservice.interfaces;

import java.util.Map;

/**
 * Created by yangzhao on 2018/5/11.
 * 通用网络请求接口
 */

public interface IBeInCommNetwork {
    void postOkHttp(String url,Map<String,String> map,MyCallBack myCallBack);
}
