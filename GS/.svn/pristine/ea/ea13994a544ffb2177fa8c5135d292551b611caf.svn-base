package com.example.gs.gonser.govenmentservice.controller;

import com.example.gs.gonser.govenmentservice.base.BaseActivity;
import com.example.gs.gonser.govenmentservice.base.BaseFragment;
import com.example.gs.gonser.govenmentservice.interfaces.IRefreshUI;
import com.example.gs.gonser.govenmentservice.interfaces.MyCallBack;
import com.example.gs.gonser.govenmentservice.model.BeInCommModel;
import com.example.gs.gonser.govenmentservice.model.BeInCommModelFrag;

import java.util.Map;

/**
 * Created by yangzhao on 2018/5/22.
 */

public class BeInCommControllerFrag {
    private BaseFragment baseFragment;
    private IRefreshUI refreshUI;
    private BeInCommModelFrag model;

    public BeInCommControllerFrag(BaseFragment baseFragment, IRefreshUI refreshUI) {
        this.baseFragment = baseFragment;
        this.refreshUI = refreshUI;
        this.model = new BeInCommModelFrag();
    }

    public void postData(String url, Map<String,String> map){
        model.postOkHttp(url, map, new MyCallBack() {
            @Override
            public void onSuccess(final String json) {
                baseFragment.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refreshUI.success("提交成功",json);
                    }
                });
            }

            @Override
            public void onFailure(final int code) {
                baseFragment.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refreshUI.fails("提交失败",code);
                    }
                });
            }
        });
    }
}
