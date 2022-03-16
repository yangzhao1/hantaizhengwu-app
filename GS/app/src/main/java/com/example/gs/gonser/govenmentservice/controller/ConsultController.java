package com.example.gs.gonser.govenmentservice.controller;

import android.app.Dialog;

import com.example.gs.gonser.govenmentservice.base.BaseActivity;
import com.example.gs.gonser.govenmentservice.interfaces.IConsultRefreshUI;
import com.example.gs.gonser.govenmentservice.interfaces.MyCallBack;
import com.example.gs.gonser.govenmentservice.model.ConsultModel;

import java.util.Map;

/**
 * Created by yangzhao on 2018/5/21.
 * 咨询投诉控制类
 */

public class ConsultController   {
    private BaseActivity baseActivity;
    private IConsultRefreshUI consultRefreshUI;
    private Dialog dialog = null;
    private ConsultModel model;
    public ConsultController(BaseActivity baseActivity,IConsultRefreshUI consultRefreshUI) {
        this.baseActivity = baseActivity;
        this.consultRefreshUI = consultRefreshUI;
        this.model = new ConsultModel();
    }

    /**
     * 部门数据
     * @param url
     * @param map
     */
    public void getDepartment(String url, Map<String,String> map){
        dialog = baseActivity.showLoading();
        model.getDepartmentList(url, map, new MyCallBack() {
            @Override
            public void onSuccess(final String json) {
                if (dialog!=null){
                    dialog.dismiss();
                }
                baseActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        consultRefreshUI.departSuccess(json);
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
                        consultRefreshUI.departFail(code);
                    }
                });
            }
        });
    }

    /**
     * 事项列表
     * @param url
     * @param map
     */
    public void getItem(String url, Map<String,String> map){
        dialog = baseActivity.showLoading();
        model.getItemList(url, map, new MyCallBack() {
            @Override
            public void onSuccess(final String json) {
                if (dialog!=null){
                    dialog.dismiss();
                }
                baseActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        consultRefreshUI.itemSuccess(json);
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
                        consultRefreshUI.itemFail(code);
                    }
                });
            }
        });
    }

    /**
     * 提交咨询数据
     * @param url
     * @param map
     */
    public void postSubmit(String url, Map<String,String> map){
        dialog = baseActivity.showLoading();
        model.postSubmit(url, map, new MyCallBack() {
            @Override
            public void onSuccess(final String json) {
                if (dialog!=null){
                    dialog.dismiss();
                }
                baseActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        consultRefreshUI.submitSuccess(json);
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
                        consultRefreshUI.submitFail(code);
                    }
                });
            }
        });
    }


}
