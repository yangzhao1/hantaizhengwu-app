package com.example.gs.gonser.govenmentservice.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.base.BaseActivity;
import com.example.gs.gonser.govenmentservice.bean.Constant;
import com.example.gs.gonser.govenmentservice.bean.DoThingBean;
import com.example.gs.gonser.govenmentservice.bean.MyFacesUrl;
import com.example.gs.gonser.govenmentservice.controller.BeInCommController;
import com.example.gs.gonser.govenmentservice.interfaces.IRefreshUI;
import com.example.gs.gonser.govenmentservice.utils.LogUtils;
import com.example.gs.gonser.govenmentservice.utils.MyRatingBar;
import com.example.gs.gonser.govenmentservice.utils.SharedPreferencesUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangzhao on 2018/5/11.
 * 我去评价
 */

public class GoEvaluateActivity extends BaseActivity implements IRefreshUI {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.titletool)
    TextView titletool;
    @BindView(R.id.star1)
    MyRatingBar star1;
    @BindView(R.id.star2)
    MyRatingBar star2;
    @BindView(R.id.star3)
    MyRatingBar star3;
    @BindView(R.id.star4)
    MyRatingBar star4;
    @BindView(R.id.content)
    EditText content;
    @BindView(R.id.submit)
    TextView submit;
    private String titleText = "评价";

    private BeInCommController controller;
    private String itemid = "";
    private String busTheme = "";
    private String id = "";

    @Override
    protected int getLayout() {
        return R.layout.goeval_main;
    }

    @Override
    protected void initView() {
        controller = new BeInCommController(this,this);
        titletool.setText(titleText);
    }

    @Override
    protected void initData() {

        DoThingBean bean = getIntent().getParcelableExtra("dothing");
        itemid = bean.getCode();
        busTheme = bean.getThing();
        id = bean.getId();

        LogUtils.i(itemid+"  "+busTheme+"  "+id);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void success(String message, String json) {
        JSONObject object = null;
        try {
            object = new JSONObject(json);
            String code  = object.getString("code");
            if (code.equals("200")){
                showToast("评价成功");
                finish();
            }else {
                showToast("服务器异常");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fails(String message, int code) {
        showToast("服务器异常");

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @OnClick({R.id.back, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                break;
            case R.id.submit:
                int starSize1 = (int) star1.getStarStep();
                int starSize2 = (int) star2.getStarStep();
                int starSize3 = (int) star3.getStarStep();
                int starSize4 = (int) star4.getStarStep();

                String contentStr = content.getText().toString();
                if (starSize1==0||starSize2==0||starSize3==0||starSize4==0){
                    showToast("评价星级不能为0");
                }else if (contentStr.equals("")){
                    showToast("评论内容不能为空");
                }else if (contentStr.length()<5){
                    showToast("内容长度大于5个字");
                }else {
                    String userid = (String) SharedPreferencesUtils.getParam(this, Constant.USERID,"");
                    Map map = new HashMap();
                    map.put("userId",userid);
                    map.put("itemId",itemid);
                    map.put("businessTheme",busTheme);
                    map.put("starSize",starSize1+"");
                    map.put("qualityEvaluate",starSize2+"");
                    map.put("timeEvaluate",starSize3+"");
                    map.put("proEvaluate",starSize4+"");
                    map.put("contentEvaluate",contentStr+"");
                    map.put("flow_id",id);

//                    LogUtils.i(map.toString());
                    controller.postData(MyFacesUrl.goEvaluate,map);
                }
                break;
        }
    }
}
