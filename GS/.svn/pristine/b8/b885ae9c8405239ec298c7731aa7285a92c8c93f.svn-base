package com.example.gs.gonser.govenmentservice.model;

import com.example.gs.gonser.govenmentservice.interfaces.IConsultModel;
import com.example.gs.gonser.govenmentservice.interfaces.MyCallBack;
import com.example.gs.gonser.govenmentservice.utils.MyOkHttpClient;

import java.util.Map;

/**
 * Created by yangzhao on 2018/5/21.
 * 咨询model
 */

public class ConsultModel implements IConsultModel{

    @Override
    public void getDepartmentList(String url, Map<String,String> map, final MyCallBack callBack) {
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

    @Override
    public void getItemList(String url, Map<String,String> map,final MyCallBack callBack) {
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

    @Override
    public void postSubmit(String url, Map<String,String> map,final MyCallBack callBack) {
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
