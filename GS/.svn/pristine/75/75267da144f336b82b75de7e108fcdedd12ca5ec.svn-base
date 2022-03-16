package com.example.gs.gonser.govenmentservice.controller;

import android.app.Dialog;

import com.example.gs.gonser.govenmentservice.base.BaseActivity;
import com.example.gs.gonser.govenmentservice.base.BaseFragment;
import com.example.gs.gonser.govenmentservice.interfaces.IRefreshUI;
import com.example.gs.gonser.govenmentservice.interfaces.MyCallBack;
import com.example.gs.gonser.govenmentservice.model.LoginModel;
import com.example.gs.gonser.govenmentservice.model.RegisterModel;

import java.util.Map;

/**
 * Created by yangzhao on 2018/5/5.
 */

public class RegisterController {

    private RegisterModel registerModel;
    private IRefreshUI refreshUI;
    private BaseFragment baseFragment;
    private Dialog dialog = null;

    public RegisterController(BaseFragment baseFragment, IRefreshUI refreshUI) {
        this.registerModel = new RegisterModel();
        this.refreshUI = refreshUI;
        this.baseFragment = baseFragment;
    }

    public void postRegisterData(Map<String,String> map){
        dialog = baseFragment.showLoading();
        registerModel.goRegister(map, new MyCallBack() {
            @Override
            public void onSuccess(final String json) {
                if (dialog!=null){
                    dialog.dismiss();
                }
                baseFragment.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refreshUI.success("注册成功",json);
                    }
                });
            }

            @Override
            public void onFailure(final int code) {
                if (dialog!=null){
                    dialog.dismiss();
                }
                baseFragment.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refreshUI.fails("注册失败",code);
                    }
                });
            }
        });
    }
}
