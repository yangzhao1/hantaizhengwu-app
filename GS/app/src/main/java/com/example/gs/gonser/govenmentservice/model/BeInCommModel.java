package com.example.gs.gonser.govenmentservice.model;

import com.example.gs.gonser.govenmentservice.interfaces.IBeInCommNetwork;
import com.example.gs.gonser.govenmentservice.interfaces.MyCallBack;
import com.example.gs.gonser.govenmentservice.utils.MyOkHttpClient;

import java.util.Map;

/**
 * Created by yangzhao on 2018/5/11.
 * 界面只有一个网络请求的时候，使用通用model
 */

public class BeInCommModel implements IBeInCommNetwork{

    @Override
    public void postOkHttp(String url,Map<String, String> map, final MyCallBack myCallBack) {
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
