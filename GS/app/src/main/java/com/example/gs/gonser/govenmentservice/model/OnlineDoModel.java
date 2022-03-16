package com.example.gs.gonser.govenmentservice.model;

import com.example.gs.gonser.govenmentservice.interfaces.IConsultModel;
import com.example.gs.gonser.govenmentservice.interfaces.MyCallBack;
import com.example.gs.gonser.govenmentservice.utils.MyOkHttpClient;

import java.util.Map;

/**
 * Created by yangzhao on 2018/5/21.
 * 在线咨询model
 */

public class OnlineDoModel {

    public void getReadIKnow(String url, Map map, final MyCallBack callBack){

        MyOkHttpClient.getNetClient().postCallNet(url, map, new MyCallBack() {
            @Override
            public void onSuccess(String json) {
                callBack.onSuccess(json);
            }

            @Override
            public void onFailure(int code) {
                callBack.onFailure(code);
            }
        });
    }

    public void getItemInfo(String url, Map map, final MyCallBack callBack){

        MyOkHttpClient.getNetClient().postCallNet(url, map, new MyCallBack() {
            @Override
            public void onSuccess(String json) {
                callBack.onSuccess(json);
            }

            @Override
            public void onFailure(int code) {
                callBack.onFailure(code);
            }
        });
    }


    public void getItemHtml(String url, Map map, final MyCallBack callBack){

        MyOkHttpClient.getNetClient().postCallNet(url, map, new MyCallBack() {
            @Override
            public void onSuccess(String json) {
                callBack.onSuccess(json);
            }

            @Override
            public void onFailure(int code) {
                callBack.onFailure(code);
            }
        });
    }
}
