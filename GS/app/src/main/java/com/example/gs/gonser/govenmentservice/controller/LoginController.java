package com.example.gs.gonser.govenmentservice.controller;

import android.app.Dialog;

import com.example.gs.gonser.govenmentservice.base.BaseActivity;
import com.example.gs.gonser.govenmentservice.interfaces.IRefreshUI;
import com.example.gs.gonser.govenmentservice.interfaces.MyCallBack;
import com.example.gs.gonser.govenmentservice.model.LoginModel;

/**
 * Created by yangzhao on 2018/5/5.
 */

public class LoginController {

    private LoginModel loginModel;
    private IRefreshUI refreshUI;
    private BaseActivity loginActivity;
    private Dialog dialog = null;

    public LoginController(BaseActivity loginActivity, IRefreshUI refreshUI) {
        this.loginModel = new LoginModel();
        this.refreshUI = refreshUI;
        this.loginActivity = loginActivity;
    }

    public void postLoginData(String username,String password){
        dialog = loginActivity.showLoading();
        loginModel.goLogin(username, password, new MyCallBack() {
            @Override
            public void onSuccess(final String json) {
                if (dialog!=null){
                    dialog.dismiss();
                }
                loginActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refreshUI.success("登录成功",json);
                    }
                });            }

            @Override
            public void onFailure(final int code) {
                if (dialog!=null){
                    dialog.dismiss();
                }
                loginActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refreshUI.fails("登录失败",code);
                    }
                });
            }
        });
    }
}
