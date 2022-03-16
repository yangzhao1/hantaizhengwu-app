package com.example.gs.gonser.govenmentservice.model;

import com.example.gs.gonser.govenmentservice.interfaces.MyCallBack;
import com.example.gs.gonser.govenmentservice.utils.MyOkHttpClient;

import java.util.Map;

/**
 * Created by yangzhao on 2018/5/22.
 * fragment 一个接口通用model
 */

public class BeInCommModelFrag {

    public void postOkHttp(String url, Map<String, String> map, final MyCallBack myCallBack) {
        MyOkHttpClient.getNetClient().postCallNet(url, map, new MyCallBack() {
            @Override
            public void onSuccess(String json) {
                myCallBack.onSuccess(json);
            }

            @Override
            public void onFailure(int code) {
                myCallBack.onFailure(code);
            }
        });
    }
}
