package com.example.gs.gonser.govenmentservice.controller;

import com.example.gs.gonser.govenmentservice.base.BaseFragment;
import com.example.gs.gonser.govenmentservice.interfaces.IHomeFragRefreshUI;
import com.example.gs.gonser.govenmentservice.interfaces.MyCallBack;
import com.example.gs.gonser.govenmentservice.model.HomeFragModel;

/**
 * Created by yangzhao on 2018/5/28.
 */

public class HomeFragController {
    private BaseFragment baseFragment;
    private IHomeFragRefreshUI refreshUI;
    private HomeFragModel model;

    public HomeFragController(BaseFragment baseFragment, IHomeFragRefreshUI refreshUI) {
        this.baseFragment = baseFragment;
        this.refreshUI = refreshUI;
        model = new HomeFragModel();
    }

    public void getImagePath(String url){
        model.getImagePath(url, new MyCallBack() {
            @Override
            public void onSuccess(final String json) {
                baseFragment.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refreshUI.getImageSuccess(json);
                    }
                });
            }

            @Override
            public void onFailure(final int code) {
                baseFragment.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refreshUI.getImageFail(code);
                    }
                });
            }
        });
    }

    public void getNoticeLimit(String url){
        model.getNoticeLimit(url, new MyCallBack() {
            @Override
            public void onSuccess(final String json) {
                baseFragment.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refreshUI.noticeSuccess(json);
                    }
                });
            }

            @Override
            public void onFailure(final int code) {
                baseFragment.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refreshUI.noticeFail(code);
                    }
                });
            }
        });
    }

}
