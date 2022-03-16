package com.example.gs.gonser.govenmentservice.controller;

import android.app.Dialog;

import com.example.gs.gonser.govenmentservice.base.BaseActivity;
import com.example.gs.gonser.govenmentservice.interfaces.IConsultRefreshUI;
import com.example.gs.gonser.govenmentservice.interfaces.IOnlineDoRefreshUI;
import com.example.gs.gonser.govenmentservice.interfaces.MyCallBack;
import com.example.gs.gonser.govenmentservice.model.ConsultModel;
import com.example.gs.gonser.govenmentservice.model.OnlineDoModel;

import java.util.Map;

/**
 * Created by yangzhao on 2018/5/21.
 * 在线办理控制类
 */

public class OnlineDoController {
    private BaseActivity baseActivity;
    private IOnlineDoRefreshUI refreshUI;
    private Dialog dialog = null;
    private OnlineDoModel model;
    public OnlineDoController(BaseActivity baseActivity, IOnlineDoRefreshUI refreshUI) {
        this.baseActivity = baseActivity;
        this.refreshUI = refreshUI;
        this.model = new OnlineDoModel();
    }

    /**
     * 阅读须知
     * @param url
     * @param map
     */
    public void getReadIKnow(String url, Map<String,String> map){
//        dialog = baseActivity.showLoading();
        model.getReadIKnow(url, map, new MyCallBack() {
            @Override
            public void onSuccess(final String json) {
                if (dialog!=null){
                    dialog.dismiss();
                }
                baseActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refreshUI.readIKnowSuccess(json);
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
                        refreshUI.readIKnowFail(code);
                    }
                });
            }
        });
    }

    /**
     * 事项详情
     * @param url
     * @param map
     */
    public void getItemInfo(String url, Map<String,String> map){
        dialog = baseActivity.showLoading();
        model.getItemInfo(url, map, new MyCallBack() {
            @Override
            public void onSuccess(final String json) {
                if (dialog!=null){
                    dialog.dismiss();
                }
                baseActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refreshUI.getItemInfoSuccess(json);
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
                        refreshUI.getItemInfoFail(code);
                    }
                });
            }
        });
    }

    /**
     * 获取在线咨询html数据
     * @param url
     * @param map
     */
    public void getItemHtml(String url, Map<String,String> map){
        dialog = baseActivity.showLoading();
        model.getItemHtml(url, map, new MyCallBack() {
            @Override
            public void onSuccess(final String json) {
                if (dialog!=null){
                    dialog.dismiss();
                }
                baseActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refreshUI.getItemHtmlDataSuccess(json);
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
                        refreshUI.getItemHtmlDataFail(code);
                    }
                });
            }
        });
    }


}
