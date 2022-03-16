package com.example.gs.gonser.govenmentservice.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.base.BaseFragment;
import com.example.gs.gonser.govenmentservice.bean.Constant;
import com.example.gs.gonser.govenmentservice.bean.UserBean;
import com.example.gs.gonser.govenmentservice.ui.ComplaintActivity;
import com.example.gs.gonser.govenmentservice.ui.MyComplaintsActivity;
import com.example.gs.gonser.govenmentservice.ui.MyEvaluateActivity;
import com.example.gs.gonser.govenmentservice.ui.LoginActivity;
import com.example.gs.gonser.govenmentservice.ui.MyCollectionActivity;
import com.example.gs.gonser.govenmentservice.ui.MyDoThingActivity;
import com.example.gs.gonser.govenmentservice.ui.MyInfoActivity;
import com.example.gs.gonser.govenmentservice.ui.SettingActivity;
import com.example.gs.gonser.govenmentservice.utils.LogUtils;
import com.example.gs.gonser.govenmentservice.utils.SharedPreferencesUtils;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;


/**
 * Created by yangzhao on 2018/5/5.
 */

public class MineFragment extends BaseFragment {

    @BindView(R.id.titletool)
    TextView titletool;
    @BindView(R.id.rel)
    RelativeLayout rel;
    @BindView(R.id.headImage)
    ImageView headImage;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.loginname)
    TextView loginname;
    @BindView(R.id.myinfo)
    RelativeLayout myinfo;
    @BindView(R.id.mycollectRel)
    LinearLayout mycollectRel;
    @BindView(R.id.mythingRel)
    LinearLayout mythingRel;
    @BindView(R.id.myevaluateRel)
    LinearLayout myevaluateRel;
    @BindView(R.id.zixunRel)
    LinearLayout zixunRel;
    @BindView(R.id.tousuRel)
    RelativeLayout tousuRel;
    @BindView(R.id.mysettingRel)
    RelativeLayout mysettingRel;
    @BindView(R.id.huixinRel)
    RelativeLayout huixinRel;
    private String titleText = "我的";
    private String myinfo_json = "";
    private CropCircleTransformation cropCircleTransformation;

    @Override
    protected View getLayout(LayoutInflater inflater, ViewGroup container) {
        if (mview == null) {
            mview = inflater.inflate(R.layout.minemain, container, false);
        }
        ViewGroup parent = (ViewGroup) mview.getParent();
        if (parent != null) {
            parent.removeView(mview);
        }
        return mview;
    }

    @Override
    protected void initView() {
        cropCircleTransformation = new CropCircleTransformation(new LruBitmapPool((int)(Runtime.getRuntime().maxMemory() / 4)));

        rel.setPadding(0,getStatusBarHeight(),0,0);
        titletool.setText(titleText);
    }

    @Override
    protected void initData() {
        myinfo_json = (String) SharedPreferencesUtils.getParam(getContext(),Constant.MYINFO,"");
        LogUtils.i(myinfo_json);
        if (!TextUtils.isEmpty(myinfo_json)){
            updateUIInfo(myinfo_json);
        }
    }

    @Override
    protected void initEvent() {

    }

    @OnClick({R.id.myinfo, R.id.mycollectRel, R.id.mythingRel, R.id.myevaluateRel, R.id.zixunRel, R.id.tousuRel, R.id.mysettingRel,R.id.huixinRel})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.myinfo://个人信息--or--login
                if (judgeIsLogin()){
                    intent = new Intent(getContext(), MyInfoActivity.class);
                    startActivityForResult(intent,Constant.REQUEST_CODE_INFO);
                }
                break;
            case R.id.mycollectRel://收藏
                if (judgeIsLogin()){
                    intent = new Intent(getContext(), MyCollectionActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.mythingRel://办件
                if (judgeIsLogin()){
                    intent = new Intent(getContext(), MyDoThingActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.myevaluateRel://评价
                if (judgeIsLogin()){
                    intent = new Intent(getContext(), MyEvaluateActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.zixunRel://咨询
                if (judgeIsLogin()){
                    intent = new Intent(getContext(), MyComplaintsActivity.class);
                    intent.putExtra("comp","0");
                    startActivity(intent);
                }
                break;
            case R.id.tousuRel://投诉
                if (judgeIsLogin()){
                    intent = new Intent(getContext(), MyComplaintsActivity.class);
                    intent.putExtra("comp","1");
                    startActivity(intent);
                }
                break;
            case R.id.huixinRel://回信
                if (judgeIsLogin()){
                    intent = new Intent(getContext(), ComplaintActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.mysettingRel://设置
                intent = new Intent(getContext(), SettingActivity.class);
                startActivityForResult(intent,Constant.REQUEST_CODE_SET);
                break;
        }
    }

    private boolean judgeIsLogin(){
        if (TextUtils.isEmpty(myinfo_json)){
            String myinfo_jsonResume = (String) SharedPreferencesUtils.getParam(getContext(),Constant.MYINFO,"");
            if (!TextUtils.isEmpty(myinfo_jsonResume)){
                return true;
            }else {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivityForResult(intent,Constant.REQUEST_CODE_LOGIN);
                return false;
            }
        }else {
            return true;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==Constant.REQUEST_CODE_LOGIN && resultCode==getActivity().RESULT_OK){
            myinfo_json = (String) SharedPreferencesUtils.getParam(getContext(), Constant.MYINFO,"");
            LogUtils.i("Login--->Mine");
            updateUIInfo(myinfo_json);
        }

        if (requestCode==Constant.REQUEST_CODE_INFO && resultCode==getActivity().RESULT_OK){
            myinfo_json = (String) SharedPreferencesUtils.getParam(getContext(), Constant.MYINFO,"");
            LogUtils.i("MyInfo--->Mine");
            updateUIInfo(myinfo_json);
        }

        if (requestCode==Constant.REQUEST_CODE_SET && resultCode==getActivity().RESULT_OK){
            LogUtils.i("Setting--->Mine");
            myinfo_json = "";
            username.setText("点击登录...");
            loginname.setText("");
            Glide.with(getContext())
                    .load("")
                    .placeholder(R.mipmap.user_defalut)
                    .bitmapTransform(cropCircleTransformation)
                    .into(headImage);
        }
    }

    private void updateUIInfo(String json){
        Gson gson = new Gson();
        UserBean bean = gson.fromJson(json, UserBean.class);
//        String url = "http://192.168.0.94:8888/apppic/uploadHeadPic/20180515/7a95c5ff-e78c-45b8-b995-2bf8933b12f0.jpg";
        String url = bean.getData().getUrl().toString();
        Glide.with(getContext())
                .load(url)
                .bitmapTransform(cropCircleTransformation)
                .placeholder(R.mipmap.user_defalut)
                .into(headImage);
        username.setText(bean.getData().getUsername());
        loginname.setText(bean.getData().getOfficeId());
    }

    @Override
    public void onResume() {
        if (TextUtils.isEmpty(myinfo_json)){
            String myinfo_jsonResume = (String) SharedPreferencesUtils.getParam(getContext(),Constant.MYINFO,"");
            if (!TextUtils.isEmpty(myinfo_jsonResume)){
                updateUIInfo(myinfo_jsonResume);
                LogUtils.i("onResume+myinfo_json");
            }
        }
        LogUtils.i("onResume");
        super.onResume();
    }
}