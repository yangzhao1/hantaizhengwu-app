package com.example.gs.gonser.govenmentservice.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.base.BaseActivity;
import com.example.gs.gonser.govenmentservice.fragment.MineFragment;
import com.example.gs.gonser.govenmentservice.utils.SharedPreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangzhao on 2018/5/10.
 */

public class SettingActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.titletool)
    TextView titletool;
    @BindView(R.id.exit)
    TextView exit;
    @BindView(R.id.changePSRel)
    RelativeLayout changePSRel;

    private String titleText = "设置";

    @Override
    protected int getLayout() {
        return R.layout.setting_main;
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

    @OnClick({R.id.back, R.id.exit,R.id.changePSRel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.exit:
                SharedPreferencesUtils.clear(this);
                setResult(RESULT_OK, new Intent(this, MineFragment.class));
                finish();
                break;
            case R.id.changePSRel:
                startActivity(new Intent(this,ChangePasswordActivity.class));
                finish();
                break;
        }
    }
}
