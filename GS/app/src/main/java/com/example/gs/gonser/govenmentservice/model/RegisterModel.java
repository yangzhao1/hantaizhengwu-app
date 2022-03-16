package com.example.gs.gonser.govenmentservice.model;

import com.example.gs.gonser.govenmentservice.bean.MyFacesUrl;
import com.example.gs.gonser.govenmentservice.interfaces.ILoginModel;
import com.example.gs.gonser.govenmentservice.interfaces.IRegisterModel;
import com.example.gs.gonser.govenmentservice.interfaces.MyCallBack;
import com.example.gs.gonser.govenmentservice.utils.MyOkHttpClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangzhao on 2018/5/5.
 */

public class RegisterModel implements IRegisterModel {

    @Override
    public void goRegister(Map<String, String> map, final MyCallBack myCallBack) {

        MyOkHttpClient.getNetClient().postCallNet(MyFacesUrl.register, map, new MyCallBack() {
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
