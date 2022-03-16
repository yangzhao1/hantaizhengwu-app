package com.example.gs.gonser.govenmentservice.model;

import com.example.gs.gonser.govenmentservice.interfaces.IHomeFragModel;
import com.example.gs.gonser.govenmentservice.interfaces.MyCallBack;
import com.example.gs.gonser.govenmentservice.utils.MyOkHttpClient;

/**
 * Created by yangzhao on 2018/5/28.
 */

public class HomeFragModel implements IHomeFragModel {


    @Override
    public void getImagePath(String url, final MyCallBack callBack) {
        MyOkHttpClient.getNetClient().postCallNet(url, null, new MyCallBack() {
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
    public void getNoticeLimit(String url, final MyCallBack callBack) {
        MyOkHttpClient.getNetClient().postCallNet(url, null, new MyCallBack() {
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
