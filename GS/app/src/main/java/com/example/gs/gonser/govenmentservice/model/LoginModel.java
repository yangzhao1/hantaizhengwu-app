package com.example.gs.gonser.govenmentservice.model;

import com.example.gs.gonser.govenmentservice.bean.MyFacesUrl;
import com.example.gs.gonser.govenmentservice.interfaces.ILoginModel;
import com.example.gs.gonser.govenmentservice.interfaces.MyCallBack;
import com.example.gs.gonser.govenmentservice.utils.MyOkHttpClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangzhao on 2018/5/5.
 */

public class LoginModel implements ILoginModel {


    @Override
    public void goLogin(String username, String password, final MyCallBack myCallBack) {
        Map map = new HashMap();
        map.put("username",username);
        map.put("password",password);
        MyOkHttpClient.getNetClient().postCallNet(MyFacesUrl.login, map, new MyCallBack() {
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
