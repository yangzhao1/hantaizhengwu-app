package com.example.gs.gonser.govenmentservice.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.base.BaseActivity;
import com.example.gs.gonser.govenmentservice.interfaces.IRefreshUI;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangzhao on 2018/5/11.
 * 我的投诉
 */

public class ComplaintActivity extends BaseActivity implements IRefreshUI {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.titletool)
    TextView titletool;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    @BindView(R.id.refresh)
    TwinklingRefreshLayout refresh;

    private String titleText = "我的投诉";

    @Override
    protected int getLayout() {
        return R.layout.itemlist_activity;
    }

    @Override
    protected void initView() {
        titletool.setText(titleText);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void success(String message, String json) {

    }

    @Override
    public void fails(String message, int code) {

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
