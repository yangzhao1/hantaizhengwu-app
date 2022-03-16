package com.example.gs.gonser.govenmentservice.controller;

import android.app.Dialog;

import com.example.gs.gonser.govenmentservice.base.BaseActivity;
import com.example.gs.gonser.govenmentservice.interfaces.IRefreshUI;
import com.example.gs.gonser.govenmentservice.interfaces.MyCallBack;
import com.example.gs.gonser.govenmentservice.model.BeInCommModel;

import java.util.Map;

/**
 * Created by yangzhao on 2018/5/11.
 * 一个接口请求的时候的通用控制器
 */

public class BeInCommController {
    private BaseActivity baseActivity;
    private IRefreshUI refreshUI;
    private Dialog dialog = null;
    private BeInCommModel model;

    public BeInCommController(BaseActivity baseActivity, IRefreshUI refreshUI) {
        this.baseActivity = baseActivity;
        this.refreshUI = refreshUI;
        this.model = new BeInCommModel();
    }

    public void postData(String url, Map<String,String> map){
        dialog = baseActivity.showLoading();
        model.postOkHttp(url, map, new MyCallBack() {
            @Override
            public void onSuccess(final String json) {
                if (dialog!=null){
                    dialog.dismiss();
                }
                baseActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refreshUI.success("提交成功",json);
                    }
                });
            }

            @Override
            public void onFailure(final int code) {
                if (dialog!=null){
                    dialog.dismiss();
                }
                baseActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refreshUI.fails("提交失败",code);
                    }
                });
            }
        });
    }
}
